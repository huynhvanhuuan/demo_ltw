const filterSelectTitle = document.querySelector('.filter-select-tilte')
const selectSort = document.getElementsByName('sort')

selectSort.onchange = function () {
    filterSelectTitle.innerHTML = selectSort.options[selectSort.selectedIndex].text
}
