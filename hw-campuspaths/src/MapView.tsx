import React, {Component} from 'react';
import "./MapView.css";

interface MapViewState {
    backgroundImage: HTMLImageElement | null; // background image
}

interface MapViewProps {
    path: any; // path of buildings
}
class MapView extends Component<MapViewProps, MapViewState>{

    canvas: React.RefObject<HTMLCanvasElement>;

    constructor(props: MapViewProps) {
        super(props);
        this.state = {
            backgroundImage: null,
        };
        this.canvas = React.createRef();
    }

    componentDidMount() {
        this.fetchAndSaveImage();
        this.drawBackgroundImage();
    }

    componentDidUpdate() {
        this.drawBackgroundImage();
    }

    fetchAndSaveImage() {
        let background: HTMLImageElement = new Image();
        background.onload = () => {
            this.setState({
                backgroundImage: background
            });
        };
        background.src = "./campus_map.jpg";
    }

    // draws map
    drawBackgroundImage() {
        let canvas = this.canvas.current;
        if (canvas === null) throw Error("Unable to draw, no canvas ref.");
        let ctx = canvas.getContext("2d");
        if (ctx === null) throw Error("Unable to draw, no valid graphics context.");
        if (this.state.backgroundImage !== null) {
            canvas.width = this.state.backgroundImage.width;
            canvas.height = this.state.backgroundImage.height;
            ctx.drawImage(this.state.backgroundImage, 0, 0);
        }
        this.drawPath(ctx, this.props.path);
    }

    //
    drawPath = (ctx: any, path: any) => {
        let start = [];
        start.push(this.props.path.start.x, this.props.path.start.y);
        let coordinates = [];
        coordinates = this.props.path.path;
        let endCoordinate = coordinates[coordinates.length - 1];
        let endPoint = [];
        endPoint.push(endCoordinate.end.x, endCoordinate.end.y);
        ctx.strokeStyle = "red";
        ctx.beginPath();
        ctx.lineWidth = 10;
        for (let i = 0; i < coordinates.length; i++) {
            ctx.moveTo(coordinates[i].start.x, coordinates[i].start.y);
            ctx.lineTo(coordinates[i].end.x, coordinates[i].end.y);
            ctx.stroke();
        }
    }

    render() {
        return (
            <canvas ref={this.canvas}/>
        )
    }
}

export default MapView;
