import React, {useEffect, useState} from 'react';
import m3Service from '../API/m3Service.js';

const Home = () => {
	const [maps, setMaps] = useState([]);

	useEffect(async () =>{
	 setMaps(await m3Service.getAllMaps());
	},[]);

	return(
		<div >
			{maps.map(map => map.name)}
		</div>
		);
}

export default Home;