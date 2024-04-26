import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import AuthService from '../APIService/AuthService'
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Paper from '@mui/material/Paper';
import Draggable from 'react-draggable';
import { Button } from '@mui/material';
import AuthenticationService from '../AuthenticationService';

export default class WelcomeComponent extends Component {




    constructor(props) {
        super(props)
        //  this.showBalance = this.showBalance.bind(this)
        this.handleClickOpen = this.handleClickOpen.bind(this)
        this.handleClose = this.handleClose.bind(this)

        this.refresh = this.refresh.bind(this)
        this.state = {
            username: '',
            balance: '',
            open: false
        }
    }


    handleClickOpen = () => {
        this.setState({
            open: true
        })
    };

    handleClose = () => {
        this.setState({
            open: false
        })
    };

    refresh() {
        AuthService.getCustomer(sessionStorage.getItem('token'))
            .then(response => {
                this.setState({
                    username: response.data.username,
                    balance: response.data.balance
                })
            }).catch(error => {
                if (error.response.status === 403) {
                    AuthenticationService.logout()
                    this.props.navigate(`/login`)
                }
                console.log(error.response.data.message)
            })
    }
    componentDidMount() {
        this.refresh()
    }



    render() {
        return (
            <>
                <div className="WelcomePage">
                    <div className="container-fluid min-vh-100 py-3 px-5">


                        <h1 className='text-center text-light'>
                            Welcome To Banking Application, {this.state.username}.
                        </h1>


                        <div className="row">
                            <div className="row">
                                <div className="col-sm-6 col-md-6 col-lg-6">
                                    <Link to='/profile' style={{ textDecoration: 'none' }}>
                                        <div className="card rounded-15 bg-dark border my-3">
                                            <div className="card-title text-uppercase fw-bold p-4 text-center text-light">
                                                Profile</div>
                                        </div>
                                    </Link>
                                </div>
                                <div className="col">
                                    <Link to='/transaction' style={{ textDecoration: 'none' }}>
                                        <div className="card rounded-15 bg-dark border my-3">
                                            <div className="card-title text-uppercase fw-bold p-4 text-center text-light">
                                                Transfer</div>
                                        </div>
                                    </Link>
                                </div>
                            </div>

                            <div className="row">
                                <div className="col-sm-6 col-md-6 col-lg-6">
                                    <Link to='/beneficiary' style={{ textDecoration: 'none' }}>
                                        <div className="card rounded-15 bg-dark border my-3">
                                            <div className="card-title text-uppercase fw-bold p-4 text-center text-light">
                                                Beneficiary</div>
                                        </div>
                                    </Link>
                                </div>
                                <div className="col">
                                    <Link to='/fixed_deposit' style={{ textDecoration: 'none' }}>
                                        <div className="card rounded-15 bg-dark border my-3">
                                            <div className="card-title text-uppercase fw-bold p-4 text-center text-light">
                                                Fixed Deposit</div>
                                        </div>
                                    </Link>
                                </div>
                            </div>


                            <div className="row">
                                <div className="col-sm-6 col-md-6 col-lg-6">
                                    <Link to='/history' style={{ textDecoration: 'none' }}>
                                        <div className="card rounded-15 bg-dark border my-3">
                                            <div className="card-title text-uppercase fw-bold p-4 text-center text-light">
                                                History</div>
                                        </div>
                                    </Link>
                                </div>
                                <div className="col">
                                    <div className="card rounded-15 bg-dark border my-3">
                                        <div className="btn m-0 p-0" onClick={this.handleClickOpen}>
                                            <div className="card-title text-uppercase fw-bold p-4 text-center text-light">
                                                Balance</div>
                                        </div>



                                        <Dialog
                                            open={this.state.open}
                                            onClose={this.handleClose}
                                            PaperComponent={PaperComponent}
                                            aria-labelledby="draggable-dialog-title"
                                        >
                                            <div className="card">
                                                <div className="card-header text-center text-uppercase fw-bold" id="draggable-dialog-title">
                                                    Current Balance
                                                </div>
                                                <div className="card-body">
                                                    <p className="card-text font-italic text-monospace">
                                                        Hi {this.state.username},
                                                    </p>
                                                    <p className="card-text font-italic text-monospace">
                                                        Your current Balance is Rs. {this.state.balance}.
                                                    </p>
                                                </div>
                                                <div className="card-footer text-right">
                                                    <div onClick={this.handleClose} className="btn btn-outline-dark">
                                                        OK
                                                    </div>
                                                </div>
                                            </div>
                                        </Dialog>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </>
        )
    }
}


function PaperComponent(props) {
    return (
        <Draggable
            handle="#draggable-dialog-title"
            cancel={'[class*="MuiDialogContent-root"]'}
        >
            <Paper {...props} />
        </Draggable>
    );
}