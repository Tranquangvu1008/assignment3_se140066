/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author SE140066
 */
public class FeedbackDTO {
    private int detailID;
    private String content;
    private int point;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int detailID, String content, int point) {
        this.detailID = detailID;
        this.content = content;
        this.point = point;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    

    
    
    
}
