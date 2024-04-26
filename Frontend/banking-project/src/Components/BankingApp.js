import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import withNavigation from './WithNavigation'
import LoginComponent from './LoginComponent';
import HeaderComponent from './HeaderComponent';
import ErrorComponent from './ErrorComponent';
import WelcomeComponent from './WelcomeComponent';
import AuthenticatedRoute from '../AuthenticatedRoute';
import LogoutComponent from './LogoutComponent';
import ProfileComponent from './ProfileComponent';
import HistoryComponent from './HistoryComponent';
import TransactionComponent from './TransactionComponent';
import BeneficiaryComponent from './BeneficiaryComponent';
import FDComponent from './FDComponent';
import RegisterComponent from './RegisterComponent';
import ContactComponent from './ContactComponent';
export default class BankingApp extends Component {

    render() {
        const LoginComponentWithNavigation = withNavigation(LoginComponent);
        const LogoutComponentWithNavigation = withNavigation(LogoutComponent);

      //  const WelcomeComponentWithParams = withParams(WelcomeComponent);
        const WelcomeComponentWithNavigation=withNavigation(WelcomeComponent)
        const HeaderComponentWithNavigation = withNavigation(HeaderComponent);
        const ProfileComponentWithNavigation=withNavigation(ProfileComponent);
        const HistoryComponentWithNavigation=withNavigation(HistoryComponent);
        const TransactionComponentWithNavigation=withNavigation(TransactionComponent);
        const BeneficiaryComponentWithNavigation=withNavigation(BeneficiaryComponent);
        const FdComponentWithNavigation=withNavigation(FDComponent);
        const RegisterComponentWithNavigation=withNavigation(RegisterComponent)

        return (
            <div className="BankingApp">
                <Router>
                    <HeaderComponentWithNavigation />
                    <Routes>
                        <Route path="/welcome" element={
                            <AuthenticatedRoute>
                                <WelcomeComponentWithNavigation />
                            </AuthenticatedRoute>
                        } />
                        <Route path="/" element={<LoginComponentWithNavigation />}></Route>
                        <Route path="/login" 
                        element={<LoginComponentWithNavigation />}>
                        </Route>

                        <Route path="/register" 
                        element={<RegisterComponentWithNavigation />}>
                        </Route>
                        <Route path="/logout" element={
                            <AuthenticatedRoute>
                                <LogoutComponentWithNavigation />
                            </AuthenticatedRoute>
                        } />

                        <Route path="/profile" element={
                            <AuthenticatedRoute>
                                <ProfileComponentWithNavigation />
                            </AuthenticatedRoute>
                        } />

                        <Route path="/history" element={
                            <AuthenticatedRoute>
                                <HistoryComponentWithNavigation />
                            </AuthenticatedRoute>
                        } />
                        <Route path="/transaction" element={
                            <AuthenticatedRoute>
                                <TransactionComponentWithNavigation />
                            </AuthenticatedRoute>
                        } />

                        <Route path="/beneficiary" element={
                            <AuthenticatedRoute>
                               <BeneficiaryComponentWithNavigation/>
                            </AuthenticatedRoute>
                        } />

                        <Route path="/fixed_deposit" element={
                            <AuthenticatedRoute>
                             <FdComponentWithNavigation/>
                            </AuthenticatedRoute>
                        } />

                        <Route path='*' element={<ErrorComponent />} > </Route>
                        <Route path='/contact' element={
                            <AuthenticatedRoute>
                               <ContactComponent/>
                            </AuthenticatedRoute>
                        } > </Route>
                    </Routes>
                    {/* <FooterComponent /> */}
                </Router>

            </div>
        )
    }
}


