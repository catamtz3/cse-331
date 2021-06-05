import React, {Component} from 'react';

interface dropDownProps {
    building: any;      //List of buildings to show, passed from App
    value: any;         //Current selected item in the dropdown menu
    onChange(text: any): void;
}

class DropMenu extends Component<dropDownProps>{

    render(){
        // stores all the buildings
        let b = ["BAG,Bagley Hall (East Entrance)",
        "BAG (NE),Bagley Hall (Northeast Entrance)",
        "BGR,By George",
        "CSE,Paul G. Allen Center for Computer Science & Engineering",
        "DEN,Denny Hall",
        "EEB,Electrical Engineering Building (North Entrance)",
        "EEB (S),Electrical Engineering Building (South Entrance)",
        "GWN,Gowen Hall",
        "KNE,Kane Hall (North Entrance)",
        "KNE (E),Kane Hall (East Entrance)",
        "KNE (SE),Kane Hall (Southeast Entrance)",
        "KNE (S),Kane Hall (South Entrance)",
        "KNE (SW),Kane Hall (Southwest Entrance)",
        "LOW,Loew Hall",
        "MGH,Mary Gates Hall (North Entrance)",
        "MGH (E),Mary Gates Hall (East Entrance)",
        "MGH (S),Mary Gates Hall (South Entrance)",
        "MGH (SW),Mary Gates Hall (Southwest Entrance)",
        "MLR,Miller Hall",
        "MOR,Moore Hall",
        "MUS,Music Building (Northwest Entrance)",
        "MUS (E),Music Building (East Entrance)",
        "MUS (SW),Music Building (Southwest Entrance)",
        "MUS (S),Music Building (South Entrance)",
        "OUG,Odegaard Undergraduate Library",
        "PAA,Physics/Astronomy Building A",
        "PAB,Physics/Astronomy Building",
        "SAV,Savery Hall",
        "SUZ,Suzzallo Library",
        "T65,Thai 65",
        "FSH,Fishery Sciences Building",
        "MCC,McCarty Hall (Main Entrance)",
        "MCC (S),McCarty Hall (South Entrance)",
        "UBS,University Bookstore",
        "UBS (Secret),University Bookstore (Secret Entrance)",
        "RAI,Raitt Hall (West Entrance)",
        "RAI (E),Raitt Hall (East Entrance)",
        "ROB,Roberts Hall",
        "CHL,Chemistry Library (West Entrance)",
        "CHL (NE),Chemistry Library (Northeast Entrance)",
        "CHL (SE),Chemistry Library (Southeast Entrance)",
        "IMA,Intramural Activities Building",
        "HUB,Student Union Building (Main Entrance)",
        "HUB (West Food),Student Union Building (West Food Entrance)",
        "HUB (South Food),Student Union Building (South Food Entrance)",
        "MNY,Meany Hall (Northeast Entrance)",
        "MNY (NW),Meany Hall (Northwest Entrance)",
        "PAR,Parrington Hall",
        "MCM,McMahon Hall (Northwest Entrance)",
        "MCM (SW),McMahon Hall (Southwest Entrance)",
        "CMU,Communications Building"]


        return (
            <div>
                <select value={this.props.value} onChange={this.props.onChange}>
                    {b}
                </select>
            </div>
        )
    }
}

export default DropMenu;