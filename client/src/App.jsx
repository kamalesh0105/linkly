import{ React} from 'react'
import {BrowserRouter as Router,Routes,Route} from "react-router-dom";
import Hero from './pages/Hero';
import About from './pages/About';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Signup from './pages/Signup';
import Signin from './pages/Signin';
import ShortenUrlPage from './pages/ShortenUrl';
import ErrorPage from './pages/ErrorPage';
import { Toaster } from 'react-hot-toast';
import DashboardLayout from "./components/Dashboard/DashboardLayout";
const  App=()=>{

  return (
    <>
      <Router>
        <Navbar/>
        <Toaster position='bottom-center'/>
        <Routes>
          <Route path='/' element={<Hero />}/>
          <Route path='/about' element={<About />}/>
           <Route path="/s/:url" element={<ShortenUrlPage />} />
          <Route path='/signup' element={<Signup/>}/>
          <Route path='/signin' element={<Signin/>}/>
           <Route path='/dashboard' element={<DashboardLayout/>}/>
           <Route path="/error" element={<ErrorPage/>}/>
            <Route path="*" element={ <ErrorPage message="We can't seem to find the page you're looking for"/>} />

        </Routes>
        <Footer/>
      </Router>
    </>
  )
}

export default App
