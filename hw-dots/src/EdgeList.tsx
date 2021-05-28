/*
 * Copyright (C) 2021 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import React, {Component} from 'react';

interface EdgeListProps {
    onChange(edges: string): void;  // called when a new edge list is ready
}

interface EdgeState {
    changeText : string;                 // what's displayed in the text area
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps, EdgeState> {

    constructor(props : EdgeListProps){
        super(props);
        this.state = {
            changeText : ''
        }
    }

    textShift = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
        this.setState({
            changeText : event.target.value
        })
    }

    drawClick = () => {
        this.props.onChange(this.state.changeText)
    }

    drawClear = () => {
        this.props.onChange("")
    }

    render() {
        return (
            <div id="edge-list">
                Edges <br/>
                <textarea
                    rows={5}
                    cols={30}
                    onChange = {this.textShift}
                    value = {this.state.changeText}
                /> <br/>
                <button onClick={() => {this.drawClick()}}>Draw</button>
                <button onClick={() => {this.drawClear()}}>Clear</button>
            </div>
        );
    }
}

export default EdgeList;
