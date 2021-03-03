import React, { useState, useEffect } from 'react';
import { Link, useRouteMatch } from 'react-router-dom';
import m3Service from '../API/m3Service.js';
import Typography from '@material-ui/core/Typography';

import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';


const MapViewer = (props) => {
	const match = useRouteMatch('/view/:mapId').params.mapId;
	const [map, setMap] = useState({
		name: " ",
		x: "hi",
		categories: []
	});

	useEffect(async () =>{
	 //setMap(await m3Service.getMap(match));
	 const fetchedMap = await m3Service.getMap(match);
	 console.log("fetched", fetchedMap);
	 setMap(fetchedMap);
	},[]);

	return(
		<div >
			<Typography variant="h5" >
				{map.name}
			</Typography>
			{/*After API call has gone through:*/}
			{map.categories && map.categories.map(category => {
				return(
					<>
					<Typography variant="h6" >
						{category.name}
					</Typography>
					<TableContainer component={Paper}>
				      <Table>
				        <TableHead>
				          <TableRow>
				            <TableCell>Company Logo</TableCell>
				            <TableCell align="right">Company Name</TableCell>
				            <TableCell align="right">Company Description</TableCell>
				          </TableRow>
				        </TableHead>
				        <TableBody>
				          {category.companies.map((company) => (
				            <TableRow key={company.id}>
				              <TableCell component="th" scope="row">
				              	<img src={company.logoUrl} alt="" width="75px"/>
				              </TableCell>
				              <TableCell align="center">{company.name}</TableCell>
				              <TableCell align="left">{company.description}</TableCell>
				            </TableRow>
				          ))}
				        </TableBody>
				      </Table>
				    </TableContainer>
				    </>
				)
			})}
		</div>
		);
}

export default MapViewer;