const filterSelectTitle = document.querySelector('.filter-select-tilte')
const selectSort = document.querySelector('.filter-select')
const optionsFilter = document.querySelectorAll('.filter-select > option')

// optionsFilter.forEach(option => {
//     option.onclick = () => {
//         filterSelectTitle.innerHTML = selectSort.options[selectSort.selectedIndex].text
//         console.log(selectSort.options[selectSort.selectedIndex].text)
//     }
// })

selectSort.onclick = () => {
    filterSelectTitle.innerHTML = selectSort.options[selectSort.selectedIndex].text
}
