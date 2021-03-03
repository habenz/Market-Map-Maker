import React, {useEffect, useState} from 'react';
import {Link } from "react-router-dom";

import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import AddToPhotosIcon from '@material-ui/icons/AddToPhotos';

import m3Service from '../API/m3Service.js';

import './Page.css';

const Home = () => {
	const [maps, setMaps] = useState([]);

	useEffect(async () =>{
	 setMaps(await m3Service.getAllMaps());
	},[]);

	return(
		<div className="page">
			<Link to="/new">
				<Button
				variant="contained"
				color="primary"
				size="large"
				startIcon={<AddToPhotosIcon />}
				>
				New Market Map
				</Button>
			</Link>

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