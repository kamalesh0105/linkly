import{ React} from 'react'
import {BrowserRouter as Router,Routes,Route} from "react-router-dom";
import Hero from './pages/Hero';
import About from './pages/About';
const  App=()=>{

  return (
    <>
      <Router>
        <Routes>
          <Route path='/' element={<Hero />}></Route>
          <Route path='/about' element={<About />}></Route>
        </Routes>
      </Router>
    </>
  )
}

export default App
