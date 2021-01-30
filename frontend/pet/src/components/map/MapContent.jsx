/* global kakao */
import React, { useState, useEffect } from 'react';
// import { markerdata } from '../../assets/mapdata/MarkerData';

export default function Map(props) {
  const mapscript = (search) => {
    const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    const mapContainer = document.getElementById('map'); // 지도를 표시할 div
    const mapOption = {
      center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
      level: 7, // 지도의 확대 레벨
    };

    const map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    // 지도에 마커와 인포윈도우를 표시하는 함수입니다

    // 장소 검색 객체를 생성합니다
    const ps = new kakao.maps.services.Places();
    // 지도에 마커를 표시하는 함수입니다
    function displayMarker(place) {
      // 마커를 생성하고 지도에 표시합니다
      const marker = new kakao.maps.Marker({
        map,
        position: new kakao.maps.LatLng(place.y, place.x),
      });

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, 'click', () => {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent(
          `<div style="padding:5px;font-size:12px;">${place.place_name}</div>`,
        );
        infowindow.open(map, marker);
      });
    }
    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        const bounds = new kakao.maps.LatLngBounds();

        for (let i = 0; i < data.length; i++) {
          displayMarker(data[i]);
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
      }
    }
    // 키워드로 장소를 검색합니다
    ps.keywordSearch(`${search}+동물병원`, placesSearchCB);
  };

  useEffect(() => {
    mapscript(props.searchData);
  }, [props.searchData]);

  return (
    <>
      <div
        id="map"
        style={{
          display: 'flex',
          left: '10vw',
          top: '10vh',
          border: 'none',
          borderRadius: '25px',
          width: '80%',
          height: '70vh',
        }}
      ></div>
    </>
  );
}
