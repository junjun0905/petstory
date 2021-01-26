import React, {Component} from "react";
import "./app.css";
import Boardcreate from "./components/board-create/board-create";


class App extends Component {
  state ={
    count: 0,
  }
  render() {
    return (
      <>
        <h1>{this.state.count}</h1>
        <Boardcreate />
      </>

    );
  }
}

export default App;
