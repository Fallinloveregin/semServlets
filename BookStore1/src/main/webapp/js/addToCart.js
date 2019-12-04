// добавление в корзину
$(document).on("submit", "#addToCartForm", function (event) {
    var $form = $(this);

    $.post($form.attr("action"), $form.serialize(), function (response) {
        //
    });

    event.preventDefault(); // не дает форме стандартно отработать с перегрузкой страницы
});