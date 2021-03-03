import axios from 'axios';

const API_URL_BASE = "http://localhost:8080/api";

class mapCreationService {
	createMapWithCategories({mapName, categories}) {
		let map = {name: mapName};
		return axios.post(`${API_URL_BASE}/map/new`,{
			name: mapName
		})
			.then( res => {
				const newMapId = res.data.id;
				map.id = newMapId;
				return axios({
					url: `${API_URL_BASE}/map/addManyTo/${newMapId}`,
					method: 'post',
					data: categories.map(categoryName => {return {name: categoryName}})

				})
			})
			.then(res => {
				// reformat response
				const categories = res.data.map(({id, name}) => {return {
					id,
					name,
					companies:[]
				}});
				map.categories = categories;
				console.log("map to return:", map)
				return map;
			})
	}

	addCompaniesAndFill({categories}) {
		console.log(categories);
		return Promise.all(categories.map(category => {
			return axios({
				url: `${API_URL_BASE}/category/addManyTo/${category.id}`,
				method: 'post',
				data: category.companies.map(companyName => {return {name: companyName}})
			})
				.then((res) => {
					console.log(res.data)
					return axios({
						url: `${API_URL_BASE}/company/fill-many-details`,
						method: 'post',
						data: res.data.map(apiCompany => apiCompany.id)
					})
				})
			;
		}))
	}

}

export default new mapCreationService();