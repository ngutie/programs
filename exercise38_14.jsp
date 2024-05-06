<%@ page import="java.util.*" %>

<html>
<head>
    <title>Addition Quiz</title>
</head>
<body>
    <h1>Addition Quiz</h1>

    <%// Set the number of questions
        int correctCount = 0;

        
        if (request.getParameter("submit")!= null) {
            // Get the answers from the request parameters
            List<Integer> userAnswers = new ArrayList<>();
            for (int i = 0; i < numberOfQuestions; i++) {
                userAnswers.add(Integer.parseInt(request.getParameter("answer" + i)));
            }

            // Generate random numbers for the questions
            Random rand = new Random();
            List<Integer> num1List = new ArrayList<>();
            List<Integer> num2List = new ArrayList<>();
            for (int i = 0; i < numberOfQuestions; i++) {
                int num1 = rand.nextInt(50) + 1;
                int num2 = rand.nextInt(50) + 1;
                num1List.add(num1);
                num2List.add(num2);
            }

            // Check the answers and display the results
            for (int i = 0; i < numberOfQuestions; i++) {
                int correctAnswer = num1List.get(i) + num2List.get(i);
                if (userAnswers.get(i) == correctAnswer) {
                    correctCount++;
                    out.println("<p>Correct! The answer is " + correctAnswer + ".</p>");
                } else {
                    out.println("<p>Sorry, that's not correct. The correct answer is " + correctAnswer + ".</p>");
                }
            }

            out.println("<p>The total correct count is " + correctCount + ".</p>");
        } else {
            // Generate random numbers for the questions
            Random rand = new Random();
            List<Integer> num1List = new ArrayList<>();
            List<Integer> num2List = new ArrayList<>();
            for (int i = 0; i < numberOfQuestions; i++) {
                int num1 = rand.nextInt(50) + 1;
                int num2 = rand.nextInt(50) + 1;
                num1List.add(num1);
                num2List.add(num2);
            }

            // Ask the questions
            for (int i = 0; i < numberOfQuestions; i++) {
    %>
        <p>What is <%= num1List.get(i) %> + <%= num2List.get(i) %>?</p>
        <form action="" method="post">
            <input type="hidden" name="num1<%= i %>" value="<%= num1List.get(i) %>">
            <input type="hidden" name="num2<%= i %>" value="<%= num2List.get(i) %>">
            <input type="text" name="answer<%= i %>">
            <% if (i == numberOfQuestions - 1) { %>
                <input type="submit" name="submit" value="Submit">
            <% } %>
        </form>
    <%
            }
        }
    %>
</body>
</html>
