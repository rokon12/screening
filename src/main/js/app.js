/**
 * @author Bazlur Rahman Rokon
 * @since 12/2/16.
 */

'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import QuestionList from './components/QuestionList';

class App extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return ( <QuestionList/> )
    }
}

ReactDOM.render(<App />, document.getElementById('react'));