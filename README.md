<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>투표 설문</title>
</head>
<body>
  <h2>설문조사</h2>
  <form id="voteForm">
    <input type="email" id="email" placeholder="이메일 입력" required><br><br>
    
    <label>질문 1:</label>
    <select id="q1">
      <option value="A">A</option>
      <option value="B">B</option>
    </select><br><br>
    
    <label>질문 2:</label>
    <select id="q2">
      <option value="X">X</option>
      <option value="Y">Y</option>
    </select><br><br>

    <label>질문 3:</label>
    <select id="q3">
      <option value="1">1</option>
      <option value="2">2</option>
    </select><br><br>

    <button type="submit">쿠폰 받기</button>
  </form>

  <script>
    document.getElementById("voteForm").addEventListener("submit", function(e) {
      e.preventDefault();

      const data = {
        email: document.getElementById("email").value,
        q1: document.getElementById("q1").value,
        q2: document.getElementById("q2").value,
        q3: document.getElementById("q3").value
      };

      fetch("https://script.google.com/macros/s/AKfycbz231ZceJq94A51GTczH_YPGI6YNsrPKMOpb8sL209sUGM0OfDij4-5Iuqu2rP9bp2b2A/exec", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
      })
      .then(res => res.text())
      .then(msg => {
        alert("제출 완료! 감사합니다.");
        document.getElementById("voteForm").reset();
      })
      .catch(err => {
        console.error(err);
        alert("전송 실패. 다시 시도해주세요.");
      });
    });
  </script>
</body>
</html>
