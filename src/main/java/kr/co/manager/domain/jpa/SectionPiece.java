package kr.co.manager.domain.jpa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class SectionPiece {

    @Id
    @GeneratedValue
    Integer sectionPieceId;

    Integer sectionNumber;

    String toolName;

    String channelName;

    String channelType;

    Integer path;

    Integer absoluteStartTick;

    Integer absoluteEndTick;
    
    String sectionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MACHINING_SECTION_ID")
    MachiningSection machiningSection;
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    String setInspection;
    
    @Transient
    @JsonSerialize
    @JsonDeserialize
    List<Object> inspectionRawData;
    
    
    public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public List<Object> getInspectionRawData() {
		return inspectionRawData;
	}

	public void setInspectionRawData(List<Object> inspectionRawData) {
		this.inspectionRawData = inspectionRawData;
	}

	public String getSetInspection() {
		return setInspection;
	}

	public void setSetInspection(String setInspection) {
		this.setInspection = setInspection;
	}

	public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
        this.path = path;
    }

    public MachiningSection getMachiningSection() {
        return machiningSection;
    }

    public void setMachiningSection(MachiningSection machiningSection) {
        this.machiningSection = machiningSection;
    }

    public Integer getSectionPieceId() {
        return sectionPieceId;
    }

    public void setSectionPieceId(Integer sectionPieceId) {
        this.sectionPieceId = sectionPieceId;
    }

    public Integer getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(Integer sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public Integer getAbsoluteStartTick() {
        return absoluteStartTick;
    }

    public void setAbsoluteStartTick(Integer absoluteStartTick) {
        this.absoluteStartTick = absoluteStartTick;
    }

    public Integer getAbsoluteEndTick() {
        return absoluteEndTick;
    }

    public void setAbsoluteEndTick(Integer absoluteEndTick) {
        this.absoluteEndTick = absoluteEndTick;
    }

	@Override
	public String toString() {
		return "SectionPiece [sectionPieceId=" + sectionPieceId + ", sectionNumber=" + sectionNumber + ", toolName="
				+ toolName + ", channelName=" + channelName + ", channelType=" + channelType + ", path=" + path
				+ ", absoluteStartTick=" + absoluteStartTick + ", absoluteEndTick=" + absoluteEndTick
				+ ", machiningSection=" + machiningSection + "]";
	}
}
