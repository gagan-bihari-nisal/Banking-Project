import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import AuthenticationService from '../AuthenticationService';
import { NavLink, Navbar, Nav } from 'react-bootstrap';

export default class HeaderComponent extends Component {

    // handleSelect=(e)=>{
       
    //     console.log(e)
    // }

    render() {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        const active=window.location.pathname;
        return (
            // <header>
            //     <nav className="navbar navbar-expand-md mx-0 navbar-dark bg-dark">
            //         <div className='navbar-brand px-3 text-uppercase'>Banking Application</div>
            //         <ul className="navbar-nav">
            //             {isUserLoggedIn && <li><Link className="nav-link" to={`welcome`}>Home</Link></li>}
            //             {isUserLoggedIn && <li><Link className="nav-link" to="/contact">Contact</Link></li>}
            //         </ul>
            //         <ul className="navbar-nav navbar-collapse justify-content-end">
            //             {!isUserLoggedIn && <li><Link className="nav-link" to="/login">Login</Link></li>}
            //             {isUserLoggedIn && <li><Link className="nav-link" to="/logout" onClick={AuthenticationService.logout}>Logout</Link></li>}
            //         </ul>
            //     </nav>
            // </header>


            <>


                <Navbar  collapseOnSelect className='px-2' expand="sm" bg="dark" variant="dark">
                    <Navbar.Toggle aria-controls="navbarScroll" data-bs-toggle="collapse" data-bs-target="#navbarScroll" />
                    <Navbar.Brand  >Banking Application</Navbar.Brand>
                    <Navbar.Collapse id="navbarScroll">
                        <Nav>
                            {isUserLoggedIn && <NavLink eventKey="1" active={active==='/welcome'?true:false} as={Link} to="/welcome">Home</NavLink>}
                            {/* <NavLink  eventKey="2" as={Link} to="/about">About</NavLink> */}
                            {isUserLoggedIn && <NavLink eventKey="2"  active={active==='/contact'?true:false} as={Link} to="/contact">Contact</NavLink>}
                        </Nav>
                        <Nav   className="ms-auto" >
                            {!isUserLoggedIn && <NavLink eventKey="3"  active={active==='/login'?true:false} as={Link} to='/login'>Login</NavLink>}
                            {isUserLoggedIn && <NavLink eventKey="4"  active={active==='/logout'?true:false} onClick={AuthenticationService.logout} as={Link} to="/logout">Logout</NavLink>}

                        </Nav>
                        
                    </Navbar.Collapse>


                </Navbar>
            </>
        )
    }
}