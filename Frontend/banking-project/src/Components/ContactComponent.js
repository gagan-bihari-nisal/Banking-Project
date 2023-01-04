import React, { Component } from 'react'
import male from './avatar7.png'
export default class ContactComponent extends Component {
  render() {
    return <>
     <div className="ContactPage">
    <div className="container-fluid min-vh-100 py-5">
    <div className="row">
        

        <div className="col-md-4 offset-md-4">
            <div className="card user-card">
                <div className="card-header">
                    <h5>Profile</h5>
                </div>
                <div className="card-block">
                    <div className="user-image">
                        <img src={male} className="img-radius" alt="User-Profile"/>
                    </div>
                    <h6 className="f-w-600 m-t-25 m-b-10 text-center text-uppercase">Gagan Bihari Nisal</h6>
                    <p className="text-muted">Active | Male | Born 14 June 1999</p>
                   <hr />
                   
                    <div className="bg-c-blue counter-block m-t-10 p-20">
                        <div className="row">
                            <div className="col-4">
                            <i className="fa fa-phone" aria-hidden="true"></i>
                            
                            </div>
                            <div className="col">
                                <p>7077988588</p>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-4">
                                <i className="fa fa-envelope"></i>
                             
                            </div>
                            <div className="col">
                              
                                <p>gagannisal@gmail.com</p>
                            </div>
                        </div>

                    </div>
                    <hr />
                 
                </div>
            </div>
        </div>

	</div>
</div>
    </div>
    </>
  }
}
