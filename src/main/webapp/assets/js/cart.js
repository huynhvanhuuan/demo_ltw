const checkAllTop = document.querySelector('#check-all')
const checkAllBottom = document.querySelector('#check-all-bottom')
let listCheck = document.querySelectorAll('.c-checkbox-child');

checkAllTop.onclick = () => {
    if (checkAllTop.checked) {
        checkAllBottom.checked = true;
        for (let i = 0; i < listCheck.length; i++) {
            listCheck[i].checked = true;
        }
    } else {
        checkAllBottom.checked = false;
        for (let i = 0; i < listCheck.length; i++) {
            listCheck[i].checked = false;
        }
    }
}

checkAllBottom.onclick = () => {
    if (checkAllBottom.checked) {
        checkAllTop.checked = true;
        for (let i = 0; i < listCheck.length; i++) {
            listCheck[i].checked = true;
        }
    } else {
        checkAllTop.checked = false;
        for (let i = 0; i < listCheck.length; i++) {
            listCheck[i].checked = false;
        }
    }
}

for (let i = 0; i < listCheck.length; i++) {
    listCheck[i].onclick = () => {
        if (!listCheck[i].checked) {
            checkAllTop.checked = false;
            checkAllBottom.checked = false;
        }

        if (listCheck[i].checked) {
            let checkAll = true;
            for (let i = 0; i < listCheck.length; i++) {
                if (!listCheck[i].checked) {
                    checkAll = false;
                }
            }
            if (checkAll) {
                checkAllTop.checked = true;
                checkAllBottom.checked = true;
            }
        }
    }
}
