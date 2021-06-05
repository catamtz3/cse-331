import React, {Component} from 'react';
import DropMenu from "./path";
import Map from "./MapView";

interface AppState {
    start: string;
    startBuilding: string;
    end: string;
    endBuilding: string;
    building: any;
    paths: any | null;
}

class App extends Component<{}, AppState> {
    // constructs the initial states of everything
    constructor(props: any) {
        super(props);
        this.state = {
            start: "",
            end: "",
            startBuilding: "",
            endBuilding: "",
            building: [],
            paths: null,
        };
    }

    // sets the start point when dropdown is clicked
    updateStart = (event: { target: { value: any; }; }) => {
        let temp = event.target.value.split("-");
        this.setState({
            start: temp[0],
            startBuilding: event.target.value,
        });
    }

    // sets the end point when dropdown is clicked
    updateEnd = (event: { target: { value: any; }; }) => {
        let temp = event.target.value.split("-");
        this.setState({
            end: temp[0],
            endBuilding: event.target.value,
        });
    }

    componentDidMount() {
        this.buildings();
    }

    async buildings() {
        let response = fetch("http://localhost:4567/listBuilding");
        let temp = await response;
        let channel = temp.json();
        let parsedObject = await channel;
        this.setState({
            building: parsedObject
        });
    }

    // clears the menu
    clearButton = () => {
        this.setState({
            start: "",
            end: "",
            paths: null,
            startBuilding: "",
            endBuilding: "",
        })
    }

    // finds the shortest path between two buildings
    getPath = async (start: string, end: string) => {
        // Handles exceptions
        let user = "";
        if (start === "" && end === "") {
            alert( "wrong format. pick start and end building");
        } else if (start === "") {
            alert("pick start");
        } else if (end === "") {
            alert("pick end");
        } else if (start === end) {
            alert("can't be the same building");
        }else {
            let response = await fetch("http://localhost:4567/findPath?start=" + start + "&end=" + end);
            if (!response.ok) {
                alert("wrong input.")
            }
            let newPath = await response.json();
            this.setState({
                paths: newPath,
            });
        }
    };

    render() {
        return (
            <div>
                <p> UW Campus paths!</p>
                <DropMenu value={this.state.startBuilding} onChange={this.updateStart} building={this.state.building}/>
                <DropMenu value={this.state.endBuilding} onChange={this.updateEnd} building={this.state.building}/>
                <button onClick={() => this.getPath(this.state.start, this.state.end)}>Find Path</button>
                <button onClick={this.clearButton}>Clear</button>
                <Map path={this.state.paths}/>
            </div>

        )
    }
}

export default App;