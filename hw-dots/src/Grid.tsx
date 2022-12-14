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

interface GridProps {
    size: number;    // size of the grid to display
    width: number;   // width of the canvas on which to draw
    height: number;  // height of the canvas on which to draw
    edges: string[];
}

interface GridState {
    backgroundImage: any,  // image object rendered into the canvas (once loaded)
}

/**
 *  A simple grid with a variable size
 *
 *  Most of the assignment involves changes to this class
 */
class Grid extends Component<GridProps, GridState> {

    canvasReference: React.RefObject<HTMLCanvasElement>

    constructor(props: GridProps) {
        super(props);
        this.state = {
            backgroundImage: null  // An image object to render into the canvas.
        };
        this.canvasReference = React.createRef();
    }

    componentDidMount() {
        // Since we're saving the image in the state and re-using it any time we
        // redraw the canvas, we only need to load it once, when our component first mounts.
        this.fetchAndSaveImage();
        this.redraw();
    }

    componentDidUpdate() {
        this.redraw()
    }

    fetchAndSaveImage() {
        // Creates an Image object, and sets a callback function
        // for when the image is done loading (it might take a while).
        const background = new Image();
        background.onload = () => {
            this.setState({
                backgroundImage: background
            });
        };
        // Once our callback is set up, we tell the image what file it should
        // load from. This also triggers the loading process.
        background.src = "./image.jpg";
    }

    redraw = () => {
        if (this.canvasReference.current === null) {
            throw new Error("Unable to access canvas.");
        }
        const ctx = this.canvasReference.current.getContext('2d');
        if (ctx === null) {
            throw new Error("Unable to create canvas drawing context.");
        }

        // First, let's clear the existing drawing so we can start fresh:
        ctx.clearRect(0, 0, this.props.width, this.props.height);

        // Once the image is done loading, it'll be saved inside our state, and we can draw it.
        // Otherwise, we can't draw the image, so skip it.
        if (this.state.backgroundImage !== null) {
            ctx.drawImage(this.state.backgroundImage, 0, 0);
        }

        // Draw all the dots.
        const coordinates = this.getCoordinates();
        for (let coordinate of coordinates) {
            this.drawCircle(ctx, coordinate);
        }
        // Draw the edges
        if (this.props.edges.toString() !== "" ){
            const getEdge : string[] = this.props.edges;
            for (let i of getEdge){
                let cut = i.split(" ");
                if (cut.length !== 3){
                    alert("Please ensure the format is x1, y1 x2, y2 color");
                } else {
                    let x1: number = parseInt(cut[0].split(",")[0]);
                    let y1: number = parseInt(cut[0].split(",")[1]);
                    let x2: number = parseInt(cut[1].split(",")[0]);
                    let y2: number = parseInt(cut[1].split(",")[1]);
                    if (x1 > this.props.size || y1 > this.props.size || x2 > this.props.size || y2 > this.props.size) {
                        alert("Outside of grid boundaries.")
                    } else {
                        let start: [number, number] = [this.props.width / (this.props.size + 1) * (x1 + 1), this.props.width / (this.props.size + 1) * (y1 + 1)];
                        let end: [number, number] = [this.props.width / (this.props.size + 1) * (x2 + 1), this.props.width / (this.props.size + 1) * (y2 + 1)];
                        ctx.lineWidth = 2;
                        ctx.strokeStyle = cut[2];
                        ctx.beginPath();
                        ctx.moveTo(start[0], start[1]);
                        ctx.lineTo(end[0], end[1]);
                        ctx.stroke();
                    }
                }
            }
        }
    };

    /**
     * Returns an array of coordinate pairs that represent all the points where grid dots should
     * be drawn.
     */
    getCoordinates = (): [number, number][] => {
        let graph: [number, number][] = [];
        const w : number = this.props.width;
        const s : number = this.props.size;
        for(let i = 0; i < s; i++){
            for (let j = 0; j < s; j++){
                graph.push([(w/(s+1)) * (i+1), (w/(s+1)) *(j+1)]);
            }
        }
        return graph;
    };

    drawCircle = (ctx: CanvasRenderingContext2D, coordinate: [number, number]) => {
        ctx.fillStyle = "white";
        // Generally use a radius of 4, but when there are lots of dots on the grid (> 50)
        // we slowly scale the radius down so they'll all fit next to each other.
        const radius = Math.min(4, 100 / this.props.size);
        ctx.beginPath();
        ctx.arc(coordinate[0], coordinate[1], radius, 0, 2 * Math.PI);
        ctx.fill();
    };

    render() {
        return (
            <div id="grid">
                <canvas ref={this.canvasReference} width={this.props.width} height={this.props.height}/>
                <p>Current Grid Size: {this.props.size}</p>
            </div>
        );
    }
}

export default Grid;
