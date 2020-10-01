import React, {Component} from "react";


class HeaderComponent extends Component{

    constructor(props) {
        super(props);

        this.state = {

        }


    }


    render() {
        return(
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark badge-dark">
                        <div className="navbar-brand">Product Service System</div>
                    </nav>
                </header>

            </div>
        )
    }

}
export default HeaderComponent