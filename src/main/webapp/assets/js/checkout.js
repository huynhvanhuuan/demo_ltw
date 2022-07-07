const $$ = document

// show box change address
const btnChangeAddress = $$.querySelector('.change-address')
const boxComfirmAddress = $$.querySelector('.comfirm-address')

btnChangeAddress.onclick = () => {
    boxComfirmAddress.classList.add('show-choosing-address')
}

// hide box change address
const btnBackToPrev = $$.querySelector('.back-to-previous-address')

btnBackToPrev.onclick = () => {
    boxComfirmAddress.classList.remove('show-choosing-address')
}

const btnCloseAddress = $$.querySelector('.close-add-address')
btnCloseAddress.onclick = () => {
    $$.querySelector('.popup-add-address').classList.remove('open')
    $$.querySelector('.box-add-address').classList.remove('open')
}

const btnOpenAddress = $$.querySelector('.add-new-address')
btnOpenAddress.onclick = () => {
    $$.querySelector('.popup-add-address').classList.add('open')
    $$.querySelector('.box-add-address').classList.add('open')
}

// api cho chọn tỉnh huyện xã
const province_city = $$.querySelector('[name="province_city"]')
const district = $$.querySelector('[name="district"]')
const ward_commune = $$.querySelector('[name="ward_commune"]')

let provinceDisable = $$.createElement('option')
provinceDisable = Object.assign(provinceDisable, {
    disabled: true,
    selected: true,
    innerText: 'Vui lòng chọn tỉnh/ thành phố',
})
province_city.appendChild(provinceDisable)

let districtDisable = $$.createElement('option')
districtDisable = Object.assign(districtDisable, {
    disabled: true,
    selected: true,
    innerText: 'Vui lòng chọn quận/ huyện',
})
district.appendChild(districtDisable)

let wardCommuneDisable = $$.createElement('option')
wardCommuneDisable = Object.assign(wardCommuneDisable, {
    disabled: true,
    selected: true,
    innerText: 'Vui lòng chọn phường/ xã',
})
ward_commune.appendChild(wardCommuneDisable)

async function loadProvinceCity() {
    let data = await fetch('https://provinces.open-api.vn/api/', { method: 'GET' })
        .then(res => res.json())
        .catch(err => console.log(err))
    data.forEach(child => {
        let option = $$.createElement('option')
        option = Object.assign(option, {
            value: child.name,
            innerText: child.name,
        })
        option.setAttribute('data-code', child.code)
        province_city.appendChild(option)
    })
}

async function loadDistrict(codeProvince) {
    district.innerHTML = ''
    districtDisable.selected = true
    district.appendChild(districtDisable)

    ward_commune.innerHTML = ''
    wardCommuneDisable.selected = true
    ward_commune.appendChild(wardCommuneDisable)

    let data = await fetch(`https://provinces.open-api.vn/api/p/${codeProvince}/?depth=2`, {
        method: 'GET',
    })
        .then(res => res.json())
        .catch(err => console.log(err))
    console.log(data.districts)
    data.districts.forEach(child => {
        let option = $$.createElement('option')
        option = Object.assign(option, {
            value: child.name,
            innerText: child.name,
        })
        option.setAttribute('data-code', child.code)
        district.appendChild(option)
    })
}

async function loadWardCommune(codeDistrict) {
    ward_commune.innerHTML = ''
    wardCommuneDisable.selected = true
    ward_commune.appendChild(wardCommuneDisable)

    let data = await fetch(`https://provinces.open-api.vn/api/d/${codeDistrict}/?depth=2`, {
        method: 'GET',
    })
        .then(res => res.json())
        .catch(err => console.log(err))
    data.wards.forEach(child => {
        let option = $$.createElement('option')
        option = Object.assign(option, {
            value: child.name,
            innerText: child.name,
        })
        option.setAttribute('data-code', child.code)
        ward_commune.appendChild(option)
    })
}

loadProvinceCity().then(r => {})

$('[name="province_city"]').change(function () {
    district.disabled = false
    ward_commune.disabled = true
    loadDistrict($(this).find(':selected').data('code')).then(r => {})
})

$('[name="district"]').change(function () {
    ward_commune.disabled = false
    loadWardCommune($(this).find(':selected').data('code')).then(r => {})
})
