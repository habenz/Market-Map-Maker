import React, {useEffect, useState} from 'react';
import {Link } from "react-router-dom";
import Typography from '@material-ui/core/Typography';

import m3Service from '../API/m3Service.js';

const Home = () => {
	const [maps, setMaps] = useState([]);

	useEffect(async () =>{
	 setMaps(await m3Service.getAllMaps());
	},[]);

	return(
		<div >
			{maps.map(map => {
				return(
					<Typography variant="h6">
					<Link to={`/view/${map.id}`}> {map.name}</Link>
					</Typography>
				)
			})}
		</div>
		);
}

export default Home;

//			{maps.map(map => <Link to={`/view/${map.id}`}> {map.name}</Link>)}
