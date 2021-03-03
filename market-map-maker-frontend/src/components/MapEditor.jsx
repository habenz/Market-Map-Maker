import React, { useState, useEffect }from 'react';
import { Link, useRouteMatch } from 'react-router-dom';

import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import IconButton from '@material-ui/core/IconButton';
import PublishIcon from '@material-ui/icons/Publish';

import m3Service from '../API/m3Service.js';

import './Page.css';
import '../styling/MapEditor.css'

const MapEditor = () => {
	const match = useRouteMatch('/edit/:mapId').params.mapId;
	const [map, setMap] = useState({name:''});
	const [newMapName, setName] = useState('');

	useEffect(async () =>{
	 const fetchedMap = await m3Service.getMap(match);
	 setMap(fetchedMap);
	},[]);

	const handleChange = e => {
		setName(e.target.value);
	}

	const handleSubmit = () => {
		console.log(map.id);
		m3Service.rename(map.id, newMapName)
	}

	return(
		<div className="page">
			<Typography variant="h5" className="title" >
				{map.name}
			</Typography>
			<TextField
	          id="mapName"
	          label="New Map Name"
	          value={newMapName}
	          onChange={handleChange}
	          variant="outlined"
	        />
	        <IconButton aria-label="submit" onClick={handleSubmit}>
			  <PublishIcon/>
			</IconButton>
		</div>
		);
}

export default MapEditor;