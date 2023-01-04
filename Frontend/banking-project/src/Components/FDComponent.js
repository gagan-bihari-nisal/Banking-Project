import React, { Component } from 'react'
import { MDBTable, MDBTableHead, MDBTableBody } from 'mdbreact';
import FDService from '../APIService/FDService';
import AuthenticationService from '../AuthenticationService';

export default class FDComponent extends Component {

    constructor(props) {
        super(props)
        this.handleFd = this.handleFd.bind(this)
        this.refresh = this.refresh.bind(this)
        this.handleDelete = this.handleDelete.bind(this)
        this.tenureChange = this.tenureChange.bind(this)
        this.state = {
            errorOccured: false,
            errorMsg: '',
            isSuccess: false,
            successMsg: '',
            click: false,
            defval: 'Show FDs',
            principal: 10000,
            tenure: 1,
            rate: 4,
            fds: []
        }
    }


    refresh = () => {
        FDService.showAll(sessionStorage.getItem('token'))
            .then(response => {
                this.setState({
                    fds: response.data
                })
            }).catch(error => {
                if (error.response.status === 403) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                  }
            })
    }

    componentDidMount = () => {
        this.refresh()
    }


    handleDelete = (e) => {
        FDService.break(e, sessionStorage.getItem('token'))
            .then(response => {
                this.setState({
                    isSuccess: true,
                    errorOccured: false,
                    successMsg: 'Fixed Deposit is withdrawn'
                })
                this.refresh()
            }).catch(error => {
                if (error.response.status === 403) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                  }
                console.log(error)
                this.setState({
                    errorOccured: true,
                    isSuccess: false,
                    errorMsg: error.response.data.message
                })
            })
    }

    handleFd = (e) => {
        e.preventDefault()
        const fd = {
            principal: this.state.principal,
            tenure: this.state.tenure
        }
        const token = sessionStorage.getItem('token')
        FDService.createFd(fd, token)
            .then(response => {
                this.setState({
                    isSuccess: true,
                    errorOccured: false,
                    successMsg: 'Fixed Deposit created.'
                })
                this.refresh()
            }).catch(error => {
                if (error.response.status === 403) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                  }
                console.log(error.response.data.message)
                this.setState({
                    errorOccured: true,
                    isSuccess: false,
                    errorMsg: error.response.data.message
                })
            })

    }


    tenureChange = (e) => {
        var t = e.target.value
        if (t >= 1 && t < 3) {
            this.setState({
                [e.target.name]: e.target.value,
                rate: 4
            })
        }
        else if (t >= 3 && t < 5) {
            this.setState({
                [e.target.name]: e.target.value,
                rate: 4.5
            })
        }
        else if (t == 5) {
            this.setState({
                [e.target.name]: e.target.value,
                rate: 5.0
            })
        }
    }


    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    changeState = () => {
        if (this.state.click === true) {
            this.setState({
                click: false,
                defval: "Show FDs"
            })
        } else if (this.state.click === false) {
            this.setState({
                click: true,
                defval: "Hide FDs"
            })


        }
    }


    render() {
        return (
            <div className="FDComponent">
                <div className="container-fluid min-vh-100 py-5 px-5">

                    <div className="card border-0 shadow rounded-3 my-5">
                        <div className="card-body">
                        <div className='bg-dark text-light text-center p-3 text-uppercase mb-3' style={{border:"1px solid #000000",padding:"5px",margin:"0px"}}>
                        Create Fixed Deposit
                    </div>
                            {this.state.isSuccess && <div className="alert alert-success text-center">
                                {this.state.successMsg}
                            </div>}

                            {this.state.errorOccured && <div className="alert alert-warning text-center">
                                {this.state.errorMsg}
                            </div>}

                            <form onSubmit={this.handleFd}>
                                <div className="row">
                                    <div className="col">
                                        <div className="form-floating mb-3">
                                            <input type="number" name='principal' value={Number(this.state.principal).toString()} onChange={this.handleChange} placeholder="Enter Principal" required className="form-control" />
                                            <label htmlFor="floatingInput">Principal</label>
                                        </div>
                                    </div>

                                    <div className="col">
                                        <div className="form-floating mb-3">
                                            <select value={this.state.tenure} onChange={this.tenureChange} className="form-select" name="tenure"  >
                                                <option value="1">1</option>
                                                <option value="3">3</option>
                                                <option value="5">5</option>

                                            </select>
                                            <label htmlFor="floatingInput">Tenure</label>
                                        </div>
                                    </div>
                                    <div className="col">
                                        <div className="form-floating mb-3">
                                            <input readOnly type="number" value={this.state.rate} name="rate" placeholder="Enter Rate" required className="form-control" />
                                            <label htmlFor="floatingInput">Rate</label>
                                        </div>
                                    </div>

                                </div>



                                <div className="row ">
                                    <center>
                                        <div className="col">
                                            <button id='fd' onSubmit={this.handleFd} type='submit' className="btn btn-success  text-uppercase fw-bold" >Create FD</button>

                                        </div>
                                    </center>
                                </div>

                            </form>
                            <hr />
                            <button id='show' onClick={this.changeState} className="btn btn-dark  text-uppercase fw-bold"  >{this.state.defval}</button>
                            <div style={{ display: this.state.click ? 'block' : 'none' }}>

                                <MDBTable hover responsive className='my-3'  >
                                    <MDBTableHead >
                                        <tr className='table-dark'>
                                            <th scope='col'>S No.</th>
                                            <th scope='col'>Username</th>
                                            <th scope='col'>Principal</th>
                                            <th scope="col">Rate</th>
                                            <th scope="col">Tenure</th>
                                            <th scope="col">Maturity Amount</th>
                                            <th scope="col">Maturity Date</th>
                                            <th scope="col">Created On</th>
                                            <th scope="col">FD Id</th>
                                            <th scope="col">Actions</th>

                                        </tr>
                                    </MDBTableHead>
                                    <MDBTableBody>
                                        {this.state.fds.map((item, i) => (
                                            <tr key={i}>
                                                <td>{i + 1}</td>
                                                <td>{item.username}</td>
                                                <td>{item.principal}</td>
                                                <td>{(item.rate)}</td>
                                                <td>{(item.tenure)}</td>
                                                <td>{item.maturity_amount}</td>

                                                <td>
                                                    {(item.maturity_date)}
                                                </td>

                                                <td>
                                                    {(item.created_on)}
                                                </td>

                                                <td>
                                                    {item.fd_id}
                                                </td>
                                                <td >
                                                    <div className="btn btn-danger" onClick={(e) => {
                                                        this.handleDelete(item.fd_id)
                                                    }}>
                                                        Break
                                                    </div>
                                                </td>


                                            </tr>
                                        ))}
                                    </MDBTableBody>
                                </MDBTable>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
