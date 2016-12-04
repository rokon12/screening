/**
 * @author Bazlur Rahman Rokon
 * @since 12/2/16.
 */

'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import client from './client';
import QuestionList from './components/QuestionList';

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