import React, {Component} from "react";
import ProductService from "../services/ProductService";

class GetAllProducts extends Component{

    constructor(props) {
        super(props);

        this.state ={
            product: []
        }
        this.addProduct = this.addProduct.bind(this);
        this.editProduct = this.editProduct.bind(this);
        this.deleteProduct = this.deleteProduct.bind(this);
    }

    deleteProduct(id){
        ProductService.deleteProduct(id).then( res => {
            this.setState({product: this.state.product.filter(product => product.id !== id)});
        });
    }
    viewProduct(id){
        this.props.history.push(`/viewDetails/${id}`);
    }
    componentDidMount() {
        ProductService.getProducts().then((res) => {
            this.setState({
                product: res.data
                }

            );
        })
    }
    editProduct(id){
        this.props.history.push(`/add/${id}`)

    }

    addProduct (){
        this.props.history.push('/add/add');

    }

    render() {
        return(
            <div>
                <h2 className="text-center">Products list</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={this.addProduct}>
                        Add Product
                    </button>

                </div>
                <div className="a row">
                    <table className="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th> Product name</th>
                            <th> Product price</th>
                            <th> Product available</th>
                            <th> Actions </th>
                        </tr>
                        </thead>

                        <tbody>
                        {
                            this.state.product.map(
                                product =>
                                    <tr key={ product.id }>
                                        <td>{ product.name }</td>
                                        <td>{ product.price }</td>
                                        <td>{ product.available.toString() }</td>
                                        <td>
                                            <button onClick={()=> this.editProduct(product.id)}
                                            className="btn btn-info">
                                                Update
                                            </button>
                                            <button style={{marginLeft: "10px"}} onClick={ () =>
                                                this.deleteProduct(product.id)} className="btn btn-danger">Delete </button>
                                            <button style={{marginLeft: "10px"}} onClick={ () =>
                                                this.viewProduct(product.id)} className="btn btn-info">View </button>
                                        </td>

                                    </tr>
                            )
                        }

                        </tbody>
                    </table>
                </div>

            </div>
        )
    }

}
export default GetAllProducts