import { Route, Routes } from "react-router-dom";
import ShortenUrlPage from "./pages/ShortenUrl";
import Hero from './pages/Hero';
import About from './pages/About';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Signup from './pages/Signup';
import Signin from './pages/Signin';
import ErrorPage from './pages/ErrorPage';
import { Toaster } from 'react-hot-toast';
import PrivateRoute from "./PrivateRoute";
import DashboardLayout from "./components/Dashboard/DashboardLayout";
const AppRouter=()=>{
    const hideHeaderFooter = location.pathname.startsWith("/s");
    return ( 
        <>
        {!hideHeaderFooter && <Navbar /> }
        <Toaster position='bottom-center'/>
        <Routes>
          <Route path='/' element={<Hero />}/>
          <Route path='/about' element={<About />}/>
           <Route path="/s/:url" element={<ShortenUrlPage />} />
         <Route path="/signup" element={<PrivateRoute publicPage={true}><Signup /></PrivateRoute>} />
          <Route path="/login" element={<PrivateRoute publicPage={true}><Signin /></PrivateRoute>} />
          
        <Route path="/dashboard" element={ <PrivateRoute publicPage={false}><DashboardLayout /></PrivateRoute>} />

           <Route path="/error" element={<ErrorPage/>}/>
            <Route path="*" element={ <ErrorPage message="We can't seem to find the page you're looking for"/>} />

        </Routes>
        {!hideHeaderFooter && <Footer />}
      </>
    );
}
export default AppRouter;
export const SubDomainRouter=()=>{
    return (
        <Routes>
            <Route path=":/url" element={<ShortenUrlPage />} />
        </Routes>
    )
}