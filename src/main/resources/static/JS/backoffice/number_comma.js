/**
* @author : 전판근
*/

$(function () {
  $('.sales-income').text(
    $('.sales-income')
      .text()
      .replace(/\B(?=(\d{3})+(?!\d))/g, ',')
  );

  $('.sales-revenue').text(
    $('.sales-revenue')
      .text()
      .replace(/\B(?=(\d{3})+(?!\d))/g, ',')
  );

  $('.sales-cancel').text(
    $('.sales-cancel')
      .text()
      .replace(/\B(?=(\d{3})+(?!\d))/g, ',')
  );

  $('.sales-price').each(function (index, value) {
    let price = $(value).text();

    $(this).text(price.replace(/\B(?=(\d{3})+(?!\d))/g, ','));
  });

  $('.reserve-price').each(function (index, value) {
    let price = $(value).text();

    $(this).text(price.replace(/\B(?=(\d{3})+(?!\d))/g, ','));
  });

  $('.reserve-count').text(
    $('.reserve-count')
      .text()
      .replace(/\B(?=(\d{3})+(?!\d))/g, ',')
  );

  $('.qna-count').text(
    $('.qna-count')
      .text()
      .replace(/\B(?=(\d{3})+(?!\d))/g, ',')
  );

  $('.review-count').text(
    $('.review-count')
      .text()
      .replace(/\B(?=(\d{3})+(?!\d))/g, ',')
  );

  $('.room-price').each(function (index, value) {
  	let price = $(value).text();
  	
  	$(this).text(price.replace(/\B(?=(\d{3})+(?!\d))/g, ','));
  });
})
