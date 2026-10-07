package chapter03.lesson07;

public class tsad {

    public static void main(String[] args) {
        /*
         * 댓글 여러 개가 같은 게시글 하나를 참조한다
            -> 댓글 기준으로 다대일
            -> 여러 행이 있는 jpa_comment에 post_id FK 배치
            -> JpaComment.post가 FK를 관리
            -> 필요할 때만 JpaPost.comments 반대 방향 추가
         *
         */
    }

}
