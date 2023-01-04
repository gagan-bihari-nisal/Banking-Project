import React, { Component } from 'react'
import AuthService from '../APIService/AuthService'
import TransactionService from '../APIService/TransactionService'
import AuthenticationService from '../AuthenticationService';

export default class TransactionComponent extends Component {


    constructor(props) {
        super(props)
        this.state = {
            customer: {},
            errorOccured: false,
            errorMsg: '',
            isSuccess: false,
            receiver: '',
            amount: null
        }
        this.handleChange = this.handleChange.bind(this)
        this.sendClicked = this.sendClicked.bind(this)
        this.refresh = this.refresh.bind(this)
    }

    handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }
    sendClicked = (e) => {
        e.preventDefault()
        TransactionService.doTransaction(sessionStorage.getItem('token'), this.state.receiver, this.state.amount)
            .then(response => {
                //   console.log(response.data)
                this.setState({
                    isSuccess: true,
                    errorOccured: false
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

    componentDidMount() {
        this.refresh()
    }

    refresh() {
        AuthService.getCustomer(sessionStorage.getItem('token'))
            .then(response => {
                this.setState({
                    customer: response.data
                })
            })
            .catch(error => {
                console.log(error.response.data.message)
                if (error.response.status === 403) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                }
                this.setState({
                    errorOccured: true,
                    errorMsg: error.response.data.message
                })
            })
    }

    render() {
        return (
            <>

                <div className="TransactionComponent">
                    <div className="container-fluid min-vh-100 py-5 px-5">
                    <div className="card border-0 shadow rounded-3 my-5">
                                    <div className="card-body">
                                        <div className='bg-dark text-light text-center p-3 text-uppercase mb-3' style={{ border: "1px solid #000000", padding: "5px", margin: "0px" }}>
                                            Transfer money
                                        </div>
                                        {this.state.isSuccess && <div className="alert alert-success text-center">
                                            Transaction Successful
                                        </div>}

                                        {this.state.errorOccured && <div className="alert alert-warning text-center">
                                            {this.state.errorMsg}
                                        </div>}
                                        <form onSubmit={this.sendClicked}>
                                            <div className="form-floating mb-3">
                                                <input readOnly value={this.state.customer.username} name='sender' type="text" className="form-control" id="floatingInputSender" />
                                                <label htmlFor="floatingInput">My Username</label>
                                            </div>

                                            <div className="form-floating mb-3">
                                                <input readOnly value={this.state.customer.balance} name='currentBalance' type="number" className="form-control" id="floatingInputCurrentBalance" />
                                                <label htmlFor="floatingInput">Current Balance</label>
                                            </div>
                                            <div className="form-floating mb-3">
                                                <input required type="text" value={this.state.receiver} onChange={this.handleChange} name='receiver' className="form-control" id="floatingInputReceiver" placeholder="Enter receiver username" />
                                                <label htmlFor="floatingInput">Receiver</label>
                                            </div>

                                            <div className="form-floating mb-3">
                                                <input required type="number" name='amount' value={Number(this.state.amount).toString()} onChange={this.handleChange} className="form-control" id="floatingInputAmount" placeholder="Enter amount to transfer" />
                                                <label htmlFor="floatingInput">Amount</label>
                                            </div>

                                            <div className="d-grid">
                                                <button name='send' type='submit' className="btn btn-success text-uppercase fw-bold">Send</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                    </div>
                </div>
              
            </>
        )
    }
}
