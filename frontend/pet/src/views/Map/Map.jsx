import React, { useState } from 'react';
import MapContent from '../../components/map/MapContent';
import MapSearch from '../../components/map/MapSearch';

function KakaoMap() {
  const [searchData, setSearchData] = useState('유성구');

  const onHandleSearch = (value) => {
    setSearchData(value);
  };

  return (
    <div className="kakaomap">
      <MapSearch onSearchData={onHandleSearch} />
      <MapContent searchData={searchData} />
    </div>
  );
}

export default KakaoMap;
