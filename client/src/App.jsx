import{ React} from 'react'
import {BrowserRouter as Router,Routes,Route} from "react-router-dom";
import Hero from './pages/Hero';
import About from './pages/About';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Signup from './pages/Signup';
const  App=()=>{

  return (
    <>
      <Router>
        <Navbar/>
        <Routes>
          <Route path='/' element={<Hero />}></Route>
          <Route path='/about' element={<About />}></Route>
          <Route path='/signup' element={<Signup/>}></Route>
        </Routes>
        <Footer/>
      </Router>
    </>
  )
}

export default App
