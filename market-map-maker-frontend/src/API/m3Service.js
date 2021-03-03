import axios from 'axios';

const API_URL_BASE = "http://localhost:8080/api";

class m3Service {
	 getAllMaps() {
		return axios.get(`${API_URL_BASE}/map/all`)
			.then(res => res.data)
	}

}

export default new m3Service();