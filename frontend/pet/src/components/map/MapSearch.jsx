import React, { useRef, useState } from 'react';
import { withRouter } from 'react-router-dom';

function MapSearch(props) {
  const searchRef = useRef(null);
  const onSubmitHandler = (e) => {
    e.preventDefault();
    props.onSearchData(searchRef.current.value);
  };

  return (
    <form onSubmit={onSubmitHandler}>
      <input
        type="text"
        ref={searchRef}
        placeholder="가고싶은 동물병원의 해당구를 검색하세요"
      />
      <button type="submit">검색하기</button>
    </form>
  );
}
export default withRouter(MapSearch);
