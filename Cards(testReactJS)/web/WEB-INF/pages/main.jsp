<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.34/browser.min.js"></script>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/content.css" var="contentCss" />
    <spring:url value="/resources/css/table.css" var="tableCss" />
    <spring:url value="/resources/js/cardsData.js" var="cardsJS" />
    <spring:url value="/resources/js/finalCards.js" var="finalCards" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${contentCss}" rel="stylesheet" />
    <link href="${tableCss}" rel="stylesheet" />
    <script src="${cardsJS}"></script>
    <script src="${finalCards}"></script>
</head>

<body>
<div class="content-list">
    <h1 style="margin-left: 25px;">Cards</h1>
    <div id="content" style="width: 50%; float: left;"></div>
</div>

<script>
    function next(url) {
        document.location=url;
        return true;
    }
</script>

<script type="text/babel">

    var Card = React.createClass({

        getInitialState: function() {
            return {
                openable: false,
            };
        },

        setCardsOpenableFalse: function() {
            firstCard.setState({openable: false});
            secondCard.setState({openable: false});
        },

        setCardsUndefined: function() {
            firstCard = secondCard = undefined;
        },

        openCard: function(e) {
            if (firstCard === undefined) {
                firstCard = this;
                this.setState({openable: true});
            } else if (firstCard !== undefined && secondCard === undefined) {
                secondCard = this;
                this.setState({openable: true});
            }
            if (firstCard !== undefined && secondCard !== undefined) {
                if (firstCard.props.id == secondCard.props.id) {
                    this.setState({openable: true});
                    this.setCardsUndefined();
                } else {
                    setTimeout(this.setCardsOpenableFalse, 1000);
                }
                setTimeout(this.setCardsUndefined, 1100);
            }
        },

        render: function() {
            var openable = this.state.openable;
            return (
                    <td>
                        <img src = {this.props.back} onClick={this.openCard} className={'content-tile__link ' + (openable ? 'none': '')} />
                        <img src={this.props.front} className={'content-tile__link ' + (openable ? '': 'none')} />
                            <p className={(openable ? '': 'none')}>{this.props.name}</p>
                    < / td >
            );
        }
    });

    var FirstRow = React.createClass({
        getInitialState: function() {
            return {
                allCards: this.getCards()
            };
        },

        getRandomInteger: function(min, max) {
            var rand = min + Math.random() * (max + 1 - min);
            rand = Math.floor(rand);
            return rand;
        },

        getCardsIdValues: function() {
            var cardsId = new Array();
            for(var i = 0; i < 8; i++) {
                cardsId[i] = this.getRandomInteger(1, 52);
            }
            return cardsId;
        },

        getCardsById: function() {
            var cards = new Array();
            var cardsId = this.getCardsIdValues();
            for (var i = 0; i < cardsId.length; i++) {
                for (var j = 0; j < CARDS.length; j++) {
                    if (cardsId[i] == CARDS[j].id) {
                        cards[i] = CARDS[j];
                    }
                }
            }
            return cards;
        },

        getCards: function() {
            var cards = this.getCardsById();
            var cardsObj = new Array();
            var cardsAttribute = new Array();
            var newCards = new Array();
            for (var i = 0; i < 16; i++) {
                cardsAttribute[i] = this.getRandomInteger(1, 200);
            }
            for (var i = 0, j = 8; i < cards.length; i++, j++) {
                cardsObj[i] = {number: cardsAttribute[i], card: cards[i]};
                cardsObj[j] = {number: cardsAttribute[j], card: cards[i]};
            }
            cardsObj.sort(function (a, b) {
                return (a.number > b.number) ? 1 : ((b.number > a.number) ? -1 : 0);
            });
            for (var i = 0; i < cardsObj.length; i++) {
                newCards[i] = cardsObj[i].card;
            }
            return newCards;
        },

        render: function() {
            var cards = this.state.allCards;
            if (finalCards.length == 0) {
                finalCards = cards;
            }
            var row = this.props.row;
            switch (row) {
                case "1": return (
                            <tr>
                                {
                                    finalCards.slice(0, 4).map(function (el) {
                                        return <Card id = {el.id} name = {el.name} back = {el.back} front = {el.front} />;
                                    })
                                }
                            </tr>
                        );
                case "2": return (
                            <tr>
                                {
                                    finalCards.slice(4, 8).map(function (el) {
                                        return <Card id = {el.id} name = {el.name} back = {el.back} front = {el.front} />;
                                    })
                                }
                            </tr>
                        );
                case "3": return (
                            <tr>
                                {
                                    finalCards.slice(8, 12).map(function (el) {
                                        return <Card id = {el.id} name = {el.name} back = {el.back} front = {el.front} />;
                                    })
                                }
                            </tr>
                        );
                case "4": return (
                            <tr>
                                {
                                    finalCards.slice(12, 16).map(function (el) {
                                        return <Card id = {el.id} name = {el.name} back = {el.back} front = {el.front} />;
                                    })
                                }
                            </tr>
                        );
            }
        }
    });

    ReactDOM.render(
            <table className={'table'}>
                <FirstRow row="1"/>
                <FirstRow row="2"/>
                <FirstRow row="3"/>
                <FirstRow row="4"/>
                <a className={'submit'} href="/"><b>Next</b></a>
            </table>, document.getElementById("content"));

</script>

</body>
</html>
