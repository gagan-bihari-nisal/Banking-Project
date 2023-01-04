import React, { Component } from 'react'
import AuthService from '../APIService/AuthService'
import female from "./avatar3.png";
import male from './avatar7.png'
import AuthenticationService from '../AuthenticationService';

export class ProfileComponent extends Component {

  constructor(props) {
    super(props)
    this.state = {
      customer: {},
      firstName: '',
      lastName: '',
      gender: '',
      dob: '',
      fatherName: '',

      phone: '',
      state: '',
      city: '',
      district: '',
      pincode: ''
    }
    this.handleChange = this.handleChange.bind(this)
    this.updateUser = this.updateUser.bind(this)
    this.refresh = this.refresh.bind(this)

    // this.formatDate = this.formatDate(this)
  }

  updateUser = (e) => {

    e.preventDefault()
    const firstName = this.state.firstName
    const lastName = this.state.lastName
    const fatherName = this.state.fatherName
    const gender = this.state.gender
    const dob = this.state.dob

    const token = sessionStorage.getItem('token')
    const contact = {
      phone: this.state.phone,
      state: this.state.state,
      city: this.state.city,
      district: this.state.district,
      pincode: this.state.pincode
    }

    console.log("we are inside update")
    AuthService.updateUser(firstName, lastName, fatherName, gender, dob, contact, token)
      .then(response => {
        console.log(response.data)
        this.refresh()

      }).catch(error => {
        console.log(error)
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


  componentDidMount() {
    this.refresh()
  }


  refresh = () => {

    AuthService.getCustomer(sessionStorage.getItem('token'))
      .then(response => {
        this.setState({
          customer: response.data,
          //    contact: response.data.contact,
          firstName: response.data.firstName,
          lastName: response.data.lastName,
          gender: response.data.gender,
          dob: response.data.dob,
          fatherName: response.data.fatherName,

          phone: response.data.contact.phone,
          state: response.data.contact.state,
          city: response.data.contact.city,
          district: response.data.contact.district,
          pincode: response.data.contact.pincode


        })

      }).catch(error => {

        console.log(error)

        if (error.response.status === 403) {
          AuthenticationService.logout()
          this.props.navigate(`/login`)
        }
      })

  }




  render() {
    return (<>



      <div className="ProfilePage">
        <div className="container-fluid min-vh-100 bg-transparent">
          <div className="row px-5 py-4">
            <div className="card border-0 shadow rounded-3 my-1">
              <div className="card-body p-0">
                <form onSubmit={this.updateUser}>
                  <div className="row border-style-none m-0 p-0">
                    <div className="col-md-3 border-right">
                      <div className="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img className="rounded-circle mt-5" src={this.state.gender === "FEMALE" ? female : this.state.gender === "MALE" ? male : ""} width="90px" alt="Unspecified" />
                        <span className="font-weight-bold">{this.state.firstName + " " + this.state.lastName}</span>
                        <span className="text-black-50">{this.state.customer.username} </span>
                        <span className='font-weight-bold text-success'>
                          Available Balance: Rs.{this.state.customer.balance}
                        </span>
                        <span style={{ fontSize: '9px' }} className='font-weight-light  text-secondary'>
                          Created on {this.state.customer.createdOn}
                        </span>
                      </div>
                    </div>
                    <div className="col-md-5 border-right">
                      <div className="p-3 py-5">
                        {/* <div className="d-flex justify-content-between align-items-center mb-3">
                            <h3 className="card-title text-center  fw-bold fs-25">Edit Profile</h3>
                          </div> */}
                        <div className='bg-dark text-light text-center p-3 text-uppercase mb-3' style={{ border: "1px solid #000000", padding: "5px", margin: "0px" }}>
                          Edit Profile
                        </div>

                        <div className="row">
                          <div className="col">
                            <div className="form-floating mb-3">
                              <input type="text" name="firstName" onChange={this.handleChange} value={this.state.firstName} placeholder="Enter full name" required className="form-control" />
                              <label htmlFor="floatingInput">First Name</label>
                            </div>
                          </div>


                          <div className="col">
                            <div className="form-floating mb-3">
                              <input type="text" name="lastName" onChange={this.handleChange} value={this.state.lastName} placeholder="Enter last name" required className="form-control" />
                              <label htmlFor="floatingInput">Last Name</label>
                            </div>
                          </div>

                        </div>

                        <div className="row">
                          <div className="col">
                            <div className="form-floating mb-3">
                              <select className="form-select" onChange={this.handleChange} value={this.state.gender} aria-label="Gender" name="gender">
                                <option value="MALE">MALE</option>
                                <option value="FEMALE">FEMALE</option>
                                <option value="OTHERS">OTHERS</option>
                              </select>
                              <label htmlFor="floatingInput">Gender</label>
                            </div>
                          </div>
                          <div className="col">
                            <div className="form-floating mb-3">
                              <input type="date" name="dob" value={this.state.dob} onChange={this.handleChange} className="form-control" />
                              <label htmlFor="floatingInput">Date of Birth</label>
                            </div>
                          </div>
                        </div>

                        <div className="row">
                          <div className="col">
                            <div className="form-floating mb-3">
                              <input type="text" name="fatherName" value={this.state.fatherName} onChange={this.handleChange} placeholder="Enter father name" required className="form-control" id="floatingInputUsername" />
                              <label htmlFor="floatingInput">Father Name</label>
                            </div>
                          </div>
                          <div className="col">
                            <div className="form-floating mb-3">
                              <input type="tel" value={this.state.phone} onChange={this.handleChange} name="phone" pattern="[123456789][0-9]{9}" required placeholder="Enter phone number" className="form-control" />
                              <label htmlFor="floatingInput">Phone</label>
                            </div>
                          </div>
                        </div>

                        <div className="row">
                          <div className="col">
                            <div className="form-floating mb-3">
                              <input type="text" value={this.state.state} onChange={this.handleChange} name="state" placeholder="Enter state" required className="form-control" id="floatingInputUsername" />
                              <label htmlFor="floatingInput">State</label>
                            </div>
                          </div>
                          <div className="col">
                            <div className="form-floating mb-3">
                              <input type="text" value={this.state.city} onChange={this.handleChange} name="city" placeholder="Enter city" required className="form-control" id="floatingInputUsername" />
                              <label htmlFor="floatingInput">City</label>
                            </div>
                          </div>
                        </div>
                        <div className="row">
                          <div className="col">
                            <div className="form-floating mb-3">
                              <input type="text" value={this.state.district} onChange={this.handleChange} name="district" placeholder="Enter district" required className="form-control" id="floatingInputUsername" />
                              <label htmlFor="floatingInput">District</label>
                            </div>
                          </div>
                          <div className="col">
                            <div className="form-floating mb-3">
                              <input type="text" value={this.state.pincode} onChange={this.handleChange} name="pincode" placeholder="Enter pincode" required className="form-control" id="floatingInputUsername" />
                              <label htmlFor="floatingInput">Pincode</label>
                            </div>
                          </div>
                        </div>

                        <div className=" text-center">
                          <div className="col">
                            <button name='update' type='submit' className="btn btn-success  text-uppercase fw-bold" >
                              Edit Profile
                            </button>

                          </div>
                        </div>
                      </div>
                    </div>



                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>


    </>
    )
  }
}

export default ProfileComponent