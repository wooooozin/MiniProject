class Pager {
    long totalPostCount; // 전체게시글수
    int totalPages; // 만들어지는 페이지 수
    int postsPerPage = 10; // 한페이지당보여지는글의수
    int pagePerBlock = 10; // 페이지네비게이션에서 보여주는 블럭수
    int currentBlockNumber; // 현재 페이지번호
    int firstBlockNumber; // 표시되는 첫번째 블럭 번호
    int lastBlockNumber; // 표시되는 마지막 블럭 번호

    public Pager(long totalPostCount) {
        this.totalPostCount = totalPostCount;
        if (totalPostCount == 0) { // 게시글 수가 0이면
            this.totalPages = 1; // 최종 페이지 수는 1
        } else { // 게시글 수가 있으면
            this.totalPages = (int) Math.ceil((double) totalPostCount / postsPerPage); // 전체 페이지 수 계산
        }
    }

    public String html(long pageIndex) {
        currentBlockNumber = (int) ((pageIndex - 1) / pagePerBlock); // 현재 페이지의 인덱스 계산
        firstBlockNumber = currentBlockNumber * pagePerBlock + 1; // 현재 인덱스에 따른 첫번쨰 블럭 번호 계산
        lastBlockNumber = Math.min(firstBlockNumber + pagePerBlock, totalPages); // 마지막 번호 계산

        StringBuilder sb = new StringBuilder();
        sb.append("<a href='#'>[처음]</a>\n");
        sb.append("<a href='#'>[이전]</a>\n\n");
        if (totalPostCount == 0) { // 0일 때
            sb.append(String.format("<a href='#' class='on'>%d</a>\n", totalPages)); // 페이지 1로 고정
        } else {
            for (int i = firstBlockNumber; i <= lastBlockNumber; i++) {
                if (i == pageIndex) {
                    sb.append(String.format("<a href='#' class='on'>%d</a>\n", i)); // 해당 페이지 강조
                } else {
                    sb.append(String.format("<a href='#'>%d</a>\n", i)); // 강조 안함
                }
            }
        }
        sb.append("\n<a href='#'>[다음]</a>\n");
        sb.append("<a href='#'>[마지막]</a>\n");
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        long totalPostCount = 127;
        long pageIndex = 13;

        Pager pager = new Pager(totalPostCount);
        System.out.println(pager.html(pageIndex));
    }
}
