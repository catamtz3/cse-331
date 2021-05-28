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

/* A simple TextField that only allows numerical input */

import React, {Component} from 'react';

interface GridSizePickerProps {
    value: string;                    // text to display in the text area
    onChange(newSize: number): void;  // called when a new size is picked
}

interface GridSizePickerDisplay {
    displayValue: string;
}

class GridSizePicker extends Component<GridSizePickerProps, GridSizePickerDisplay> {

    onInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const newSize: number = parseInt(event.target.value);
        this.setState({
            displayValue: event.target.value
        })
        if (newSize < 0 || newSize > 100){
            console.log("Incorrect input. Please input a number between 1 and 100.");
        } else {
            this.props.onChange(newSize); // Tell our parent component about the new size.
        }
    };

    render() {
        return (
            <div id="grid-size-picker">
                <label>
                    Grid Size:
                    <input
                        value={this.props.value}
                        onChange={this.onInputChange}
                        type="number"
                        min={1}
                        max={100}
                    />
                </label>
            </div>
        );
    }
}

export default GridSizePicker;
