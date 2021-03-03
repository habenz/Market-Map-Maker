import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';

import mapCreationService from '../API/mapCreationService.js';

import Button from '@material-ui/core/Button';
import ArrowForwardIcon from '@material-ui/icons/ArrowForward';
import BackupOutlinedIcon from '@material-ui/icons/BackupOutlined';
import TextField from '@material-ui/core/TextField';
import IconButton from '@material-ui/core/IconButton';
import RemoveCircleOutlineIcon from '@material-ui/icons/RemoveCircleOutline';
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import Typography from '@material-ui/core/Typography';

import './Page.css';
import '../styling/MapCreator.css'

const MapCreator = () => {
	const history = useHistory();

	const [phase, setPhase] = useState(0);
	const [apiMap, setAPIMap] = useState({});
	const [form, updateForm] = useState({
		mapName: '',
		categories: ['', '']
	});

	const completeFirstStage = async () => {
		await handleSubmit0();
		setPhase(1);
	}

	const handleMapNameChange = e => {
		updateForm({...form, mapName:e.target.value})
	}
	const handleChange0 = e => {
		let newCategories = form.categories
		newCategories[e.target.id.slice(-2,-1)] = e.target.value;
		updateForm({...form, categories:newCategories});
	}

	const handleSubmit0 = async () => {
		// make API calls to create map with empty categories
		const createdMap = await mapCreationService.createMapWithCategories(form);
		console.log("createdMap", createdMap);
		setAPIMap(createdMap);
		return createdMap;
	}

	const handleChange1 = e => {
		const categoryIndex = apiMap.categories
			.findIndex(category => category.id == e.target.id);
		const newCategories = apiMap.categories;
		newCategories[categoryIndex].companies = e.target.value.split(", ");
		setAPIMap({...apiMap, categories:newCategories});
	}

	const handleSubmit1 = async () => {
		await mapCreationService.addCompaniesAndFill(apiMap);
		console.log("pushing")
		history.push(`/view/${apiMap.id}`)
	}

	const removeCategory = index => {
		updateForm({...form, 
			categories:form.categories.filter((_,i) => i != index)});
	}

	const addCategory = () => {
		updateForm({...form, categories:[...form.categories,""]});
	}

	if (!phase){
	return(
		<div className="page">
			<form >
				<div  className="input_wrapper">
					<TextField
			          id="form.mapName"
			          label="Map Name"
			          value={form.mapName}
			          onChange={handleMapNameChange}
			          variant="outlined"
			        />
			        <IconButton aria-label="add" onClick={addCategory}>
					  <AddCircleOutlineIcon/>
					</IconButton>
				</div>

				<div >
					{form.categories.map((categoryName, i)=>{
						return(
							<div key={i} className="input_wrapper">
							<TextField
					          id={`form.categories[${i}]`}
					          label={`Category Name ${i+1}`}
					          value={form.categories[i]}
					          onChange={handleChange0}
					          variant="outlined"
					        />
					        <IconButton 
					        	aria-label="delete" 
					        	color="secondary" 
					        	onClick={() => removeCategory(i)}>
							  <RemoveCircleOutlineIcon/>
							</IconButton>
					        </div>
							)
					})}
				</div>
			</form>
			<Button
			variant="contained"
			color="primary"
			size="large"
			startIcon={<ArrowForwardIcon />}
			onClick={completeFirstStage}/>
		</div>
		);
	} else {
		return(
			<div className='page'>
				<Typography variant="h4">
					{apiMap.name}
				</Typography>

				{apiMap.categories.map(category=>{
					return (
						<div key={category.id}>
						<Typography variant="h5">
							{category.name}:
						<TextField
				          id={category.id}
				          label="Comma Separated Company Names"
				          value={category.companies.join(", ")}
				          multiline
				          fullWidth
				          rowsMax={4}
				          size="medium"
				          onChange={handleChange1}
				          variant="outlined"
				        />
						</Typography>
						</div>
					)
				})}
			<Button
			variant="contained"
			color="primary"
			size="large"
			startIcon={<BackupOutlinedIcon />}
			onClick={handleSubmit1}
			>
				Create Map
			</Button>
			</div>
		)
	}
}

export default MapCreator;