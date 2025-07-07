import React, { useState } from 'react';
import { Link,useNavigate } from 'react-router-dom';
import { FaBars, FaTimes } from 'react-icons/fa';
import {useStoreContext} from '../contextApi/ContextApi';

const Navbar = () => {
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const {token,setToken}=useStoreContext();

  const toggleMenu = () => setIsOpen(!isOpen);
  const closeMenu = () => setIsOpen(false);
  const onLogOutHandler = () => {
    setToken(null);
    localStorage.removeItem("JWT_TOKEN");
    navigate("/login");
  };

  const navLinkClass = "px-3 py-1 rounded-md bg-gray-900 text-white";

  return (
    <nav className="border-b border-gray-950/5 dark:border-white/10 w-full fixed top-0 z-50 bg-[#0c0c1c]">
      <div className="h-14 flex justify-between items-center text-white px-4">
        {/* Logo */}
        <div className="flex items-center gap-2">
          <img src="/vite.svg" alt="Logo" className="h-6 w-6" />
          <h1 className="text-xl font-bold">Linkly</h1>
        </div>

        {/* Desktop Nav */}
        <div className="hidden md:flex space-x-4">
          <Link to="/" className={navLinkClass}>Home</Link>
          <Link to="/about" className={navLinkClass}>About</Link>
           <Link to="/dashboard" className={navLinkClass}>Dashboard</Link>
          {!token && (<Link to="/login" className={navLinkClass}>Signin</Link>)}
          {token && (<Link to="/" onClick={onLogOutHandler} className={navLinkClass}>Logout</Link>)}
        </div>

        {/* Hamburger Icon */}
        <div className="md:hidden">
          <button onClick={toggleMenu}>
            {isOpen ? <FaTimes size={24} /> : <FaBars size={24} />}
          </button>
        </div>
      </div>

      {/* Mobile Menu */}
      {isOpen && (
        <div className="md:hidden bg-[#0c0c1c] px-4 pb-4 flex flex-col space-y-2">
          <Link to="/" onClick={closeMenu} className={navLinkClass}>Home</Link>
          <Link to="/about" onClick={closeMenu} className={navLinkClass}>About</Link>
          <Link to="/signup" onClick={closeMenu} className={navLinkClass}>Signup</Link>
          <Link to="/logout" onClick={closeMenu} className={navLinkClass}>Logout</Link>
        </div>
      )}
    </nav>
  );
};

export default Navbar;
