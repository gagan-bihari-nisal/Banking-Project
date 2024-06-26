import React, { Component } from 'react'
import AuthService from '../APIService/AuthService'
import AuthenticationService from '../AuthenticationService'
import { LinearProgress } from '@mui/material'
export default class LoginComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            showProgress: false,
            username: '',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false,
            errorMsg: ''
        }
        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)
        this.handleRegister = this.handleRegister.bind(this)
        //   this.handleCheck = this.handleCheck.bind(this);
    }


    handleRegister = (e) => {
        e.preventDefault()

        this.props.navigate('/register')
    }
    loginClicked = (e) => {
        e.preventDefault();
        this.setState({ showProgress: true })
        if (this.state.username === '' || this.state.password === '') {
            this.setState({ errorMsg: 'Every field is mandatory', hasLoginFailed: true })
        } else {

            AuthService.getToken(this.state.username, this.state.password)
                .then(response => {
                    AuthenticationService.registerSuccessfulLogin(this.state.username, response.data.token)
                    this.setState({ showProgress: false })
                    this.props.navigate(`/welcome`)
                }).catch(error => {
                    this.setState({ showProgress: false })

                    this.setState({ showSuccessMessage: false })
                    this.setState({ hasLoginFailed: true })
                    this.setState({ errorMsg: error.response.data.message })
                })
        }
    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }



    render() {



        return <div className="LoginComponent ">
            {
                this.state.showProgress && <LinearProgress color='success' />
            }

            {/* <div className='container mx-5 my-5 '>

            </div> */}
            {/* testing */}


            <div className="container-fluid min-vh-100" >

                <div className="row">
                    <div className="col-sm-9 col-md-7 col-lg-4 mx-auto">
                        <div className="card border-0 shadow rounded-3 my-5">
                            <div className="card-title text-center mx-3 my-3 fw-bold fs-3 text-uppercase">Login</div>
                             <div className="card-body mx-3">
                             <form onSubmit={this.loginClicked}>
                                    {this.state.hasLoginFailed && <div className='alert alert-danger'>{this.state.errorMsg}</div>}

                                    <div className="form-floating mb-3">
                                        
                                        <input name='username' value={this.state.username} onChange={this.handleChange} required type="text" className="form-control" id="floatingInputUsername" placeholder="Enter your username" />
                                        <label for="floatingInput">Username</label>
                                    </div>
                                    <div className="form-floating mb-3">
                                        <input name='password' value={this.state.password} onChange={this.handleChange} required type="password" className="form-control" id="floatingInputPassword" placeholder="Enter your password" />
                                        <label for="floatingInput">Password</label>
                                    </div>
                                    <div className="d-grid">
                                        <button id='submit' className="btn btn-success btn-login text-uppercase fw-bold" type="submit">Log in</button>
                                    </div>
                                    <hr className="my-4" />
                                    <div className="d-grid">
                                        <button id='register' onClick={this.handleRegister} className="btn btn-info btn-login text-uppercase fw-bold" >Register</button>
                                    </div>

                                </form>
                             </div>
                        </div>
                    </div>
                </div>


                {/* <div className="row">
                    <div className="col-sm-9 col-md-7 col-lg-5 mx-auto ">
                        <div className="card border-0 shadow rounded-3 my-5 ">

                            <div className="card-body p-4 p-sm-5">
                                <h3 className="card-title text-center mb-4 fw-bold text-uppercase">Login</h3>

                                <form onSubmit={this.loginClicked}>
                                    {this.state.hasLoginFailed && <div className='alert alert-danger'>{this.state.errorMsg}</div>}

                                    <div className="form-floating mb-3">
                                        <input name='username' value={this.state.username} onChange={this.handleChange} required type="text" className="form-control" id="floatingInputUsername" placeholder="Enter your username" />
                                        <label for="floatingInput">Username</label>
                                    </div>
                                    <div className="form-floating mb-3">
                                        <input name='password' value={this.state.password} onChange={this.handleChange} required type="password" className="form-control" id="floatingInputPassword" placeholder="Enter your password" />
                                        <label for="floatingInput">Password</label>
                                    </div>
                                    <div className="d-grid">
                                        <button id='submit' className="btn btn-success btn-login text-uppercase fw-bold" type="submit">Log in</button>
                                    </div>
                                    <hr className="my-4" />
                                    <div className="d-grid">
                                        <button id='register' onClick={this.handleRegister} className="btn btn-info btn-login text-uppercase fw-bold" >Register</button>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div> */}
            </div>




            {/* testing */}
            {/* <div className="row ">
                <div className="col-3 mx-auto my-auto ">
                    <div className="card border-5 shadow rounded-3 ">
                        <div className="card-title text-center text-uppercase fw-bold m-3 ">Login</div>
                        {this.state.hasLoginFailed && <div className='alert alert-warning m-3'>{this.state.errorMsg}</div>}
                        <div className="card-body">

                            <div className="form-floating mb-3">
                                <input name='username' value={this.state.username} onChange={this.handleChange} required type="text" className="form-control" id="floatingInputUsername" placeholder="Enter your username" />
                                <label htmlFor="floatingInput">Username</label>
                            </div>
                            <div className="form-floating mb-3">
                                <input name='password' value={this.state.password} onChange={this.handleChange} required type="password" className="form-control" id="floatingInputPassword" placeholder="Enter your password" />
                                <label htmlFor="floatingInput">Password</label>
                            </div>
                            <div className="d-grid">
                                <button name='login' onClick={this.loginClicked} className="btn btn-success text-uppercase fw-bold">Log in</button>
                            </div>
                            <hr />
                            <div className="d-grid">
                                <button name='register' className="btn btn-info text-uppercase fw-bold text-light" >Register</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div> */}
        </div>
    }
}