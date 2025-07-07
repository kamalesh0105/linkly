import{ React} from 'react'
import {BrowserRouter as Router,Routes,Route} from "react-router-dom";
import {getApps} from './utils/helper';

const  App=()=>{
  const CurrentApp=getApps();

  return (
    <>
     <Router>
      <CurrentApp/>
     </Router>
    </>
  )
}

export default App
