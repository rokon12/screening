/**
 * @author Bazlur Rahman Rokon
 * @since 12/2/16.
 */

'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const QuestionList = require('./components/QuestionList');

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {questions: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/v1/questions'})
            .done(response => {
                console.log(response);
                this.setState({questions: response.entity._embedded.questions});
            });
    }

    render() {
        return (
            <QuestionList questions={this.state.questions}/>
        )
    }
}

ReactDOM.render(<App />, document.getElementById('react'));