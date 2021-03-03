import React, {useEffect, useState} from 'react';
import {Link, useHistory } from "react-router-dom";

import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import AddToPhotosIcon from '@material-ui/icons/AddToPhotos';
import IconButton from '@material-ui/core/IconButton';
import RemoveCircleOutlineIcon from '@material-ui/icons/RemoveCircleOutline';
import EditIcon from '@material-ui/icons/Edit';

import m3Service from '../API/m3Service.js';

import './Page.css';

const Home = () => {
	const history = useHistory();
	const [maps, setMaps] = useState([]);
	const [pruning, setPruning] = useState(false);

	useEffect(async () =>{
	 setMaps(await m3Service.getAllMaps());
	},[]);

	const togglePruning = () => {
		setPruning(!pruning);
	}
	const deleteMap = async id => {
		console.log(id)
		await m3Service.delete(id);
		history.push("/");
	}

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
					<Link to={`/view/${map.id}`}> {map.name} </Link>
				        {pruning && <IconButton 
				        	aria-label="delete" 
				        	color="secondary" 
				        	onClick={() => deleteMap(map.id)}>
						  <RemoveCircleOutlineIcon/>
						</IconButton>}
					</Typography>
				)
			})}
				<Button
				variant="contained"
				color="primary"
				size="large"
				startIcon={<EditIcon />}
				onClick={togglePruning}
				>
				Edit Map list
				</Button>
		</div>
		);
}

export default Home;