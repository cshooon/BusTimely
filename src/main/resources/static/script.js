function searchByRoute() {
    const routeName = document.getElementById('routeNameOnly').value;
    search(`/busStops/searchByRoute?routeName=${routeName}`, 'resultLeft');
}

function searchByRouteAndStation() {
    const routeName = document.getElementById('routeName').value;
    const stationName = document.getElementById('stationName').value;
    search(`/busStops/searchByRouteAndStation?routeName=${routeName}&stationName=${stationName}`, 'resultRight');
}

function search(url, resultId) {
    const container = document.getElementById(resultId);
    // Clear previous results
    container.innerHTML = '';

    fetch(url)
        .then(response => response.json())
        .then(data => {
            data.forEach(item => {
                const div = document.createElement('div');
                div.className = 'bus-info';

                const busName = document.createElement('div');
                busName.className = 'bus-name';
                busName.textContent = `정류장: ${item.stNm}`;
                div.appendChild(busName);

                const arrMsg1 = document.createElement('div');
                arrMsg1.textContent = `1번째 버스: ${item.arrmsg1}`;
                div.appendChild(arrMsg1);

                const arrMsg2 = document.createElement('div');
                arrMsg2.textContent = `2번째 버스: ${item.arrmsg2}`;
                div.appendChild(arrMsg2);

                container.appendChild(div);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
