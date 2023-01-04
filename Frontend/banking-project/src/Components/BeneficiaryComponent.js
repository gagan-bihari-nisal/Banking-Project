import React, { Component } from 'react'
import BeneficiaryService from '../APIService/BeneficiaryService'
import { MDBTable, MDBTableHead, MDBTableBody } from 'mdbreact';
import AuthenticationService from '../AuthenticationService';
export class BeneficiaryComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            errorOccured: false,
            errorMsg: '',
            isSuccess: false,
            successMsg: '',
            click: false,
            defval: 'Show Beneficiaries',
            firstName: '',
            lastName: '',
            relation: 'PARENT',
            phone: '',
            percent: '',
            bfs: []
        }
        this.handleAddBeneficiary = this.handleAddBeneficiary.bind(this)
        this.changeState = this.changeState.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.handleDelete = this.handleDelete.bind(this)
        this.refreshBeneficiaries = this.refreshBeneficiaries.bind(this)
    }
    handleAddBeneficiary = (e) => {
        e.preventDefault()
        const beneficiary = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            relation: this.state.relation,
            phone: this.state.phone,
            percent: this.state.percent
        }
        const token = sessionStorage.getItem('token')
        BeneficiaryService.addBeneficiary(beneficiary, token)
            .then(response => {
                this.setState({
                    isSuccess: true,
                    errorOccured: false,
                    successMsg: 'Beneficiary Added'
                })
                this.refreshBeneficiaries()
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


    changeState = () => {
        if (this.state.click === true) {
            this.setState({
                click: false,
                defval: "Show Beneficiaries"
            })
        } else if (this.state.click === false) {
            this.setState({
                click: true,
                defval: "Hide Beneficiaries"
            })


        }
    }

    componentDidMount() {
        this.refreshBeneficiaries()
    }

    refreshBeneficiaries() {
        BeneficiaryService.showAll(sessionStorage.getItem('token'))
            .then(response => {
                this.setState({
                    bfs: response.data
                })
            }).catch(error => {
                if (error.response.status === 403) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                  }
            })
    }



    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })

    }


    handleDelete = (e) => {
        BeneficiaryService.delete(e, sessionStorage.getItem('token'))
            .then(response => {
                this.setState({
                    isSuccess: true,
                    errorOccured: false,
                    successMsg: 'Beneficiary Deleted'
                })
                this.refreshBeneficiaries()
            }).catch(error => {
                if (error.response.status === 403) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                  }
                this.setState({
                    errorOccured: true,
                    isSuccess: false,
                    errorMsg: error.response.data.message
                })
            })
    }


    render() {
        return (
            <>
                <div className="BeneficiaryComponent">
                    <div className="container-fluid min-vh-100 py-5 px-5">

                        <div className="card border-0 shadow rounded-3 my-5">
                            <div className="card-body">
                                <div className='bg-dark text-light text-center p-3 text-uppercase mb-3' style={{ border: "1px solid #000000", padding: "5px", margin: "0px" }}>
                                    Add Beneficiary
                                </div>                                {this.state.isSuccess && <div className="alert alert-success text-center">
                                    {this.state.successMsg}
                                </div>}

                                {this.state.errorOccured && <div className="alert alert-warning text-center">
                                    {this.state.errorMsg}
                                </div>}

                                <form onSubmit={this.handleAddBeneficiary}>
                                    <div className="row">
                                        <div className="col">
                                            <div className="form-floating mb-3">
                                                <input type="text" value={this.state.firstName} onChange={this.handleChange} name="firstName" placeholder="Enter First Name" required className="form-control" />
                                                <label htmlFor="floatingInput">First Name</label>
                                            </div>
                                        </div>
                                        <div className="col">
                                            <div className="form-floating mb-3">
                                                <input type="text" value={this.state.lastName} onChange={this.handleChange} name="lastName" placeholder="Enter Last Name" required className="form-control" />
                                                <label htmlFor="floatingInput">Last Name</label>
                                            </div>
                                        </div>

                                        <div className="col">
                                            <div className="form-floating mb-3">
                                                <select value={this.state.relation} onChange={this.handleChange} className="form-select" aria-label="relation" name="relation"  >
                                                    <option value="PARENT">PARENT</option>
                                                    <option value="CHILDREN">CHILDREN</option>
                                                    <option value="SIBLINGS">SIBLINGS</option>
                                                    <option value="OTHERS">OTHERS</option>
                                                </select>
                                                <label htmlFor="floatingInput">Relation</label>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="row">

                                        <div className="col-4">
                                            <div className="form-floating mb-3">
                                                <input type="tel" value={this.state.phone} onChange={this.handleChange} name="phone" pattern="[123456789][0-9]{9}" required placeholder="Enter phone number" className="form-control" />
                                                <label htmlFor="floatingInput">Phone</label>
                                            </div>
                                        </div>
                                        <div className="col-4">
                                            <div className="form-floating mb-3">
                                                <input type="number" value={this.state.percent} onChange={this.handleChange} name="percent" min={1} max={100} placeholder="Enter percent" required className="form-control" />
                                                <label htmlFor="floatingInput">Percent</label>
                                            </div>
                                        </div>

                                    </div>


                                    <div className="row ">
                                        <center>
                                            <div className="col">
                                                <button id='benefeciary' onSubmit={this.handleAddBeneficiary} type='submit' className="btn btn-success  text-uppercase fw-bold" >Add Beneficiary</button>

                                            </div>
                                        </center>
                                    </div>

                                </form>
                                <hr />
                                <button id='show' onClick={this.changeState} className="btn btn-dark  text-uppercase fw-bold"  >{this.state.defval}</button>
                                <div style={{ display: this.state.click ? 'block' : 'none' }}>

                                    <MDBTable hover className='my-3' responsive >
                                        <MDBTableHead >
                                            <tr className='table-dark'>
                                                <th scope='col'>S No.</th>
                                                <th scope='col'>Username</th>
                                                <th scope='col'>Full Name</th>
                                                <th scope="col">Relation</th>
                                                <th scope="col">Phone</th>
                                                <th scope="col">Percent</th>
                                                <th scope="col">Added On</th>
                                                <th scope="col">Beneficiary Id</th>
                                                <th scope="col">Actions</th>
                                                {/* <th scope="col">Time</th> */}
                                                {/* <th scope="col">Status</th>
                                            <th scope="col">Transaction Id</th> */}
                                            </tr>
                                        </MDBTableHead>
                                        <MDBTableBody>
                                            {this.state.bfs.map((item, i) => (
                                                <tr key={i}>
                                                    <td>{i + 1}</td>
                                                    <td>{item.username}</td>
                                                    <td>{item.firstName + " " + item.lastName}</td>
                                                    <td>{(item.relation)}</td>
                                                    <td>{(item.phone)}</td>
                                                    <td>{item.percent}</td>

                                                    <td>
                                                        {(item.addedOn)}
                                                    </td>

                                                    <td>
                                                        {(item.bf_id)}
                                                    </td>
                                                    <td >
                                                        <div className="btn btn-danger" onClick={(e) => {
                                                            this.handleDelete(item.bf_id)
                                                        }}>
                                                            Delete
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

            </>
        )
    }
}

export default BeneficiaryComponent