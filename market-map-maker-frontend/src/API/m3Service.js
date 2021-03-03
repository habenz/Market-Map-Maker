import axios from 'axios';

const API_URL_BASE = "http://localhost:8080/api";

class m3Service {
	getAllMaps() {
		return axios.get(`${API_URL_BASE}/map/all`)
			.then(res => res.data)
	}

	// Involved method to construct full map information
	getMap(mapId) {
		let map;
		return axios.get(`${API_URL_BASE}/map/${mapId}`)
			.then(res => res.data)
			.then(data => {
				map = data;
				return axios.get(`${API_URL_BASE}/map/map-categories/${mapId}`)
			})
			.then(res => {
				map.categories = res.data.map(({id, name}) => {return {id, name}});
				console.log(map);
				return Promise.all(res.data.map(({id})=>{
					return axios.get(`${API_URL_BASE}/category/category-companies/${id}`)
				}));
			})
			.then(companiesRes => {
				const companiesPerCategory = companiesRes.map(res => res.data);
				companiesPerCategory.forEach(companies => {
					//might get empty company list
					if(companies){
						const categoryIndex = map.categories
							.findIndex(category => category.id === companies[0].category.id);
						companies.forEach(company => {
							delete company.category
						})
						map.categories[categoryIndex].companies = companies;
					}
				});
				return map;
			})
	}

}

export default new m3Service();