/**
 * @author : 전판근
 */

$(function () {
  var tag = {}
  var counter = 0

  var margin_tag_list = []

  // server로 보낼 값.
  var backoffice_tag = ''

  // 입력한 값을 태그로 생성
  function addTag(value) {
    tag[counter] = value
    counter++ // del-btn의 고유 id
  }

  // tag 안에 있는 값을 array type으로 만들어서 넘긴다.
  function marginTag() {
    margin_tag_list = []
    return Object.values(tag).filter(function (word) {
      return word !== ''
    })
  }

  function toStringTag(list) {
    backoffice_tag = ''

    for (var i = 0; i < list.length; i++) {
      if (i != list.length - 1) {
        backoffice_tag += list[i] + ','
      } else {
        backoffice_tag += list[i]
      }
    }

    console.log('backoffice tag : ', backoffice_tag)
    $('#real-input-tag').val(backoffice_tag)
  }

  $('#backoffice_tag').on('keypress', function (e) {
    var self = $(this)

    // 엔터나 스페이스바 눌렀을 때 생성
    if (e.key === 'Enter' || e.keyCode == 32) {
      var tagValue = self.val() // 값 가져오기

      // 해시태그 값이 없으면 실행되지 않음.
      if (tagValue !== '') {
        // 같은 태그가 있는지 검사. 있으면 해당 값이 array로 return
        var result = Object.values(tag).filter(function (word) {
          return word === tagValue
        })

        // 해시태그 중복 확인
        if (result.length == 0 && margin_tag_list.length < 5) {
          $('#tag-list').append(
            "<li class='tag-item'>" +
              tagValue +
              "<span class='del-btn' idx='" +
              counter +
              "'>x</span></li>",
          )
          addTag(tagValue)
          margin_tag_list = marginTag()
          toStringTag(margin_tag_list)
          self.val('')
        } else if (margin_tag_list.length >= 5) {
          $('.popup-background:eq(1)').removeClass('blind')
          $('#common-alert-popup').removeClass('blind')
          $('.common-alert-txt').text('해시태그는 최대 5개 입니다.')
          self.val('')
        } else {
          $('.popup-background:eq(1)').removeClass('blind')
          $('#common-alert-popup').removeClass('blind')
          $('.common-alert-txt').text('중복된 해시태그 입니다.')
          self.val('')
        }
      }
      e.preventDefault() // SpaceBar 시 빈공간 생기지 않도록 방지
    }
  })

  // 삭제 버튼
  // 인덱스 검사 후 삭제
  $(document).on('click', '.del-btn', function (e) {
    var index = $(this).attr('idx')
    tag[index] = ''
    margin_tag_list = marginTag()
    toStringTag(margin_tag_list)
    $(this).parent().remove()
  })
})
