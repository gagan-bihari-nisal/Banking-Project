import React, { Component } from 'react'
import HistoryService from '../APIService/HistoryService'
import AuthenticationService from '../AuthenticationService';

import { MDBTable, MDBTableHead, MDBTableBody } from 'mdbreact';
import Moment from 'react-moment';
export default class HistoryComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            history: []
        }
    }

    componentDidMount() {
        HistoryService.getAllHistory(sessionStorage.getItem('token'))
            .then(response => {
                console.log(response)
                this.setState({
                    history: response.data
                })
            }).catch(error => {
                if (error.response.status === 403) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                  }
                console.log(error.response.data.message)
            })
    }
    render() {
        return (
            <>
                <div className="HistoryComponent">
                    <div className="container-fluid min-vh-100 py-3 px-5">
                        <div className="card border-0 shadow rounded-3 my-5">

                            <div className="card-title p-3 "  style={{border:"0px solid #000000"}}>
                            <div className='bg-dark text-light text-center p-3 text-uppercase ' style={{border:"1px solid #000000",padding:"5px",margin:"0px"}}>
                       Transaction History
                    </div>
                               
                            </div>
                            <div className="card-body">

                                <MDBTable hover responsive  >
                                    <MDBTableHead >
                                        <tr className='table-dark'>
                                            <th scope='col'>#</th>
                                            <th scope='col'>You</th>
                                            <th scope="col">Sender</th>
                                            <th scope="col">Receiver</th>
                                            <th scope="col">Type</th>
                                            <th scope="col">Amount</th>
                                            <th scope="col">Time</th>
                                            <th scope="col">Status</th>
                                            <th scope="col">Transaction Id</th>
                                        </tr>
                                    </MDBTableHead>
                                    <MDBTableBody>
                                        {this.state.history.map((item, i) => (
                                            <tr key={i}>
                                                <td>{i + 1}</td>
                                                <td>{item.username}</td>
                                                <td>{(item.sender)}</td>
                                                <td>{(item.reciever)}</td>
                                                <td className={(item.type==='DEPOSIT')?'text-success':'text-danger'} >{(item.type).toUpperCase()}</td>
                                                <td>Rs.{item.amount}</td>
                                                <td>
                                                    <Moment format="DD-MM-YYYY hh:mm A">
                                                        {(item.transaction_time)}
                                                    </Moment>
                                                    {/* {(item.transaction_time).substring(0, 10)} */}
                                                </td>
                                                <td className='text-success'>
                                                    {(item.status).toUpperCase()}
                                                </td>
                                                <td>{(item.transaction_id)}</td>
                                            </tr>
                                        ))}
                                    </MDBTableBody>
                                </MDBTable>

                            </div>
                        </div>

                    </div>
                </div>















                
            </>
        )
    }
}
