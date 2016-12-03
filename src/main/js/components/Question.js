/**
    * @author Bazlur Rahman Rokon
    * @since 12/3/16.
    */

const React = require('react');
const Button = require('react-bootstrap').Button;
const ButtonToolbar = require('react-bootstrap').ButtonToolbar;

class Question extends React.Component {
    constructor(props) {
        super(props);
        this.state = {question: this.props.question};
    }

    showInside = (event) => {
        console.log("id: " + this.state.question.id)
    };

    editQuestion = (event) => {
        console.log("edit: " + this.state.question.id)
    };

    deleteQuestion = (event) => {
        console.log("delete: " + this.state.question.id)
    };

    render() {
        return (  <tr>
            <td>{this.state.question.id}</td>
            <td>{this.state.question.difficulty}</td>
            <td>{this.state.question.maxScore}</td>
            <td>{this.state.question.allocatedTime}</td>
            <td>{this.state.question.name}</td>

            <td>
                <ButtonToolbar>
                    <Button bsSize="xs" href="#" onClick={this.editQuestion}> <i className="fa fa-edit"></i> Edit</Button>
                    <Button bsSize="xs" href="#" onClick={this.showInside}> <i className="fa fa-bar-chart"></i> Inside</Button>
                    <Button bsSize="xs" href="#" onClick={this.deleteQuestion}> <i className="fa fa-remove"></i> Delete</Button>
                </ButtonToolbar>
            </td>
        </tr>)
    }
}

module.exports = Question;